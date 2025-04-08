package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Artwork;
import myexceptions.ArtWorkNotFoundException;
import myexceptions.UserNotFoundException;
import util.DBConnection;

public class CrimeAnalysisServiceImpl implements IVirtualArtGallery {

    private static Connection connection;

    public CrimeAnalysisServiceImpl() {
        connection = DBConnection.getConnection();
    }

    // 1. Add Artwork
    @Override
    public boolean addArtwork(Artwork artwork) {
        try {
            String query = "INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, artwork.getTitle());
            stmt.setString(2, artwork.getDescription());
            stmt.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime())); // ✅ Fix here
            stmt.setString(4, artwork.getMedium());
            stmt.setString(5, artwork.getImageURL());
            stmt.setInt(6, artwork.getArtistID());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // 2. Update Artwork
    @Override
    public boolean updateArtwork(Artwork artwork) {
        String sql = "UPDATE Artwork SET Title=?, Description=?, CreationDate=?, Medium=?, ImageURL=?, ArtistID=? WHERE ArtworkID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setDate(3, artwork.getCreationDate());
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageURL());
            ps.setInt(6, artwork.getArtistID());
            ps.setInt(7, artwork.getArtworkID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Remove Artwork
    @Override
    public boolean removeArtwork(int artworkID) {
        String sql = "DELETE FROM Artwork WHERE ArtworkID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, artworkID);
            int rows = ps.executeUpdate();
            if (rows == 0) throw new ArtWorkNotFoundException("Artwork with ID " + artworkID + " not found.");
            return true;
        } catch (SQLException | ArtWorkNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Get Artwork by ID
    @Override
    public Artwork getArtworkById(int artworkID) throws ArtWorkNotFoundException {
        String sql = "SELECT * FROM Artwork WHERE ArtworkID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, artworkID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                );
            } else {
                throw new ArtWorkNotFoundException("Artwork with ID " + artworkID + " not found.");
            }
        } catch (SQLException | ArtWorkNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5. Search Artworks by keyword
    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                artworks.add(new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    // 6. Add to Favorites
    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        try {
            // Check if the favorite already exists
            String checkQuery = "SELECT * FROM User_Favorite_Artwork WHERE UserID = ? AND ArtworkID = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, artworkId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("⚠️ Favorite already exists.");
                return false;
            }

            // Insert if not exists
            String insertQuery = "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, artworkId);

            int rows = insertStmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // 7. Remove from Favorites
    @Override
    public boolean removeArtworkFromFavorite(int userId, int artworkId) {
        String sql = "DELETE FROM User_Favorite_Artwork WHERE UserID=? AND ArtworkID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 8. Get User Favorite Artworks
    @Override
    public List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException {
        List<Artwork> favorites = new ArrayList<>();
        if (!userExists(userId)) {
            try { throw new UserNotFoundException("User with ID " + userId + " not found."); }
            catch (UserNotFoundException e) { e.printStackTrace(); return favorites; }
        }

        String sql = "SELECT a.* FROM Artwork a JOIN User_Favorite_Artwork ufa ON a.ArtworkID = ufa.ArtworkID WHERE ufa.UserID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                favorites.add(new Artwork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getDate("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return favorites;
    }

    // 🔍 Utility: Check if user exists
    private boolean userExists(int userId) {
        String sql = "SELECT UserID FROM User WHERE UserID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
