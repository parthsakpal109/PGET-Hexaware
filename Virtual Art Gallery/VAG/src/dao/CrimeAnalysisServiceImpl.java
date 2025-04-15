package dao;

import entity.Artwork;
import myexceptions.ArtWorkNotFoundException;
import myexceptions.UserNotFoundException;
import util.DBConnection;  // Add this import for DBConnection

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrimeAnalysisServiceImpl implements IVirtualArtGallery {

    // List to store artworks and favorites in-memory
    private List<Artwork> artworks = new ArrayList<>();
    private List<Favorite> userFavorites = new ArrayList<>();

    // Constructor to initialize with existing artworks (optional)
    public CrimeAnalysisServiceImpl(List<Artwork> artworks) {
        if (artworks != null) {
            this.artworks = artworks;
        }
    }

    @Override
    public boolean addArtwork(Artwork artwork) {
        return artworks.add(artwork);
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        for (int i = 0; i < artworks.size(); i++) {
            if (artworks.get(i).getId() == artwork.getId()) {
                artworks.set(i, artwork);
                return true;
            }
        }
        return false;  // Artwork not found
    }

    @Override
    public boolean removeArtwork(int artworkId) {
        // Remove artwork from in-memory list
        boolean removedFromList = artworks.removeIf(artwork -> artwork.getId() == artworkId);
        
        if (removedFromList) {
            // Remove artwork from the database as well
            try (Connection conn = DBConnection.getConnection()) {  // DBConnection used here
                String sql = "DELETE FROM artworks WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, artworkId);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("✅ Artwork removed from database successfully!");
                        return true;
                    } else {
                        System.out.println("❌ Artwork could not be removed from database.");
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Artwork not found in the list
    }

    @Override
    public Artwork getArtworkById(int artworkId) throws ArtWorkNotFoundException {
        for (Artwork artwork : artworks) {
            if (artwork.getId() == artworkId) {
                return artwork;
            }
        }
        throw new ArtWorkNotFoundException("Artwork with ID " + artworkId + " not found.");
    }

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> result = new ArrayList<>();
        for (Artwork artwork : artworks) {
            if (artwork.getTitle().contains(keyword) || artwork.getDescription().contains(keyword)) {
                result.add(artwork);
            }
        }
        return result;  // Returns a list of artworks that match the keyword
    }

    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        // Check if the user already has this artwork as a favorite
        for (Favorite fav : userFavorites) {
            if (fav.getUserId() == userId && fav.getArtworkId() == artworkId) {
                return false;  // Artwork already in user's favorites
            }
        }
        // Add to favorites
        userFavorites.add(new Favorite(userId, artworkId));
        return true;
    }

    @Override
    public boolean removeArtworkFromFavorite(int userId, int artworkId) {
        return userFavorites.removeIf(fav -> fav.getUserId() == userId && fav.getArtworkId() == artworkId);
    }

    @Override
    public List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException {
        List<Artwork> userFavoriteArtworks = new ArrayList<>();
        for (Favorite fav : userFavorites) {
            if (fav.getUserId() == userId) {
                // Find the artwork corresponding to the favorite
                for (Artwork artwork : artworks) {
                    if (artwork.getId() == fav.getArtworkId()) {
                        userFavoriteArtworks.add(artwork);
                    }
                }
            }
        }
        if (userFavoriteArtworks.isEmpty()) {
            throw new UserNotFoundException("No favorites found for user ID " + userId);
        }
        return userFavoriteArtworks;
    }

    // Inner class to represent a Favorite relationship between a user and artwork
    private static class Favorite {
        private int userId;
        private int artworkId;

        public Favorite(int userId, int artworkId) {
            this.userId = userId;
            this.artworkId = artworkId;
        }

        public int getUserId() {
            return userId;
        }

        public int getArtworkId() {
            return artworkId;
        }
    }
}
