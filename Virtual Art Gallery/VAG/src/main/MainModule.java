package main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.CrimeAnalysisServiceImpl;
import entity.Artwork;
import myexceptions.ArtWorkNotFoundException;
import myexceptions.UserNotFoundException;

public class MainModule {
    public static void main(String[] args) {
        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("🔹 Adding a new artwork...");
            Artwork newArt = new Artwork(0, "Dreamscape", "A surreal dream world", Date.valueOf("2024-01-01"), "Digital", "dreamscape.jpg", 1);
            boolean added = service.addArtwork(newArt);
            System.out.println("Added: " + added);

            System.out.println("\n🔹 Fetching artwork by ID...");
            Artwork fetched = service.getArtworkById(1); // try valid and invalid ids
            System.out.println("Title: " + fetched.getTitle());

            System.out.println("\n🔹 Searching artworks with keyword 'Night'...");
            List<Artwork> results = service.searchArtworks("Night");
            for (Artwork a : results) {
                System.out.println("Found: " + a.getTitle());
            }

            System.out.println("\n🔹 Adding artwork to favorites...");
            boolean favAdded = service.addArtworkToFavorite(1, 3); // try invalid user ID to test exception
            System.out.println("Added to favorites: " + favAdded);

            System.out.println("\n🔹 User's favorite artworks:");
            List<Artwork> favs = service.getUserFavoriteArtworks(1);
            for (Artwork art : favs) {
                System.out.println("❤️ " + art.getTitle());
            }

        } catch (ArtWorkNotFoundException | UserNotFoundException e) {
            System.out.println("❗ Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ General Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
