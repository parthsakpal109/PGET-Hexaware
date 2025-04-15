package dao;

import static org.junit.Assert.*;
import entity.Artwork;
import myexceptions.ArtWorkNotFoundException;
import myexceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CrimeAnalysisServiceImplTest {
    
    private CrimeAnalysisServiceImpl service;
    
    @Before
    public void setUp() {
        service = new CrimeAnalysisServiceImpl(null); // Initialize with an empty list or some mock data
    }

    @Test
    public void testAddArtwork() {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        boolean result = service.addArtwork(artwork);
        assertTrue(result); // Assert that the artwork was added successfully
    }

    @Test
    public void testUpdateArtwork() {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork); // Add artwork first
        artwork.setTitle("Updated Title");
        
        boolean updated = service.updateArtwork(artwork);
        assertTrue(updated); // Assert that the artwork was updated
    }

    @Test
    public void testRemoveArtwork() {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork); // Add artwork first
        boolean removed = service.removeArtwork(1); // Remove the artwork by ID
        assertTrue(removed); // Assert that the artwork was removed successfully
    }

    @Test
    public void testGetArtworkById() throws ArtWorkNotFoundException {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork);
        Artwork retrieved = service.getArtworkById(1);
        assertNotNull(retrieved); // Assert that the artwork was retrieved successfully
    }

    @Test(expected = ArtWorkNotFoundException.class)
    public void testGetArtworkByIdNotFound() throws ArtWorkNotFoundException {
        service.getArtworkById(999); // This ID should throw an exception
    }

    @Test
    public void testSearchArtworks() {
        Artwork artwork1 = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        Artwork artwork2 = new Artwork(2, "Another Title", "Another Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork1);
        service.addArtwork(artwork2);

        List<Artwork> results = service.searchArtworks("Test");
        assertEquals(1, results.size()); // Expect one match
    }

    @Test
    public void testAddArtworkToFavorite() {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork);
        
        boolean added = service.addArtworkToFavorite(1, 1); // User 1 adds artwork 1 to favorites
        assertTrue(added); // Assert the artwork was added to favorites
    }

    @Test
    public void testRemoveArtworkFromFavorite() {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork);
        service.addArtworkToFavorite(1, 1); // User 1 adds artwork 1 to favorites
        
        boolean removed = service.removeArtworkFromFavorite(1, 1); // User 1 removes artwork 1 from favorites
        assertTrue(removed); // Assert the artwork was removed from favorites
    }

    @Test
    public void testGetUserFavoriteArtworks() throws UserNotFoundException {
        Artwork artwork = new Artwork(1, "Test Title", "Test Description", null, "Oil", "testimage.jpg", 1);
        service.addArtwork(artwork);
        service.addArtworkToFavorite(1, 1); // User 1 adds artwork 1 to favorites
        
        List<Artwork> favorites = service.getUserFavoriteArtworks(1);
        assertEquals(1, favorites.size()); // Assert the user has 1 favorite artwork
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserFavoriteArtworksNotFound() throws UserNotFoundException {
        service.getUserFavoriteArtworks(999); // User not found, should throw an exception
    }
}
