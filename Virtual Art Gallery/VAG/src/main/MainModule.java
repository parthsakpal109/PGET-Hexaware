package main;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.CrimeAnalysisServiceImpl;
import entity.Artwork;
import myexceptions.ArtWorkNotFoundException;
import myexceptions.UserNotFoundException;

public class MainModule {
    public static void main(String[] args) {
        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl(null);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\n====== Virtual Art Gallery ======");
            System.out.println("1. Add Artwork");
            System.out.println("2. Update Artwork");
            System.out.println("3. Remove Artwork");
            System.out.println("4. Get Artwork by ID");
            System.out.println("5. Search Artworks by keyword");
            System.out.println("6. Add Artwork to Favorites");
            System.out.println("7. Remove Artwork from Favorites");
            System.out.println("8. View User Favorite Artworks");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter description: ");
                        String description = sc.nextLine();
                        System.out.print("Enter creation date (yyyy-MM-dd): ");
                        String dateStr = sc.nextLine();
                        System.out.print("Enter medium: ");
                        String medium = sc.nextLine();
                        System.out.print("Enter image URL: ");
                        String imageURL = sc.nextLine();
                        System.out.print("Enter artist ID: ");
                        int artistId = sc.nextInt();

                        Artwork a1 = new Artwork(0, title, description, new java.sql.Date(sdf.parse(dateStr).getTime()), medium, imageURL, artistId);
                        boolean added = service.addArtwork(a1);
                        System.out.println("Added: " + added);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter artwork ID to update: ");
                        int id = sc.nextInt(); 
                        sc.nextLine(); // consume newline
                        System.out.print("Enter new title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter new description: ");
                        String description = sc.nextLine();
                        System.out.print("Enter new creation date (yyyy-MM-dd): ");
                        String dateStr = sc.nextLine();
                        System.out.print("Enter new medium: ");
                        String medium = sc.nextLine();
                        System.out.print("Enter new image URL: ");
                        String imageURL = sc.nextLine();
                        System.out.print("Enter artist ID: ");
                        int artistId = sc.nextInt();

                        Artwork a2 = new Artwork(id, title, description, new java.sql.Date(sdf.parse(dateStr).getTime()), medium, imageURL, artistId);
                        boolean updated = service.updateArtwork(a2);
                        System.out.println("Updated: " + updated);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.print("Enter artwork ID to remove: ");
                    int id3 = sc.nextInt();
                    boolean removed = service.removeArtwork(id3);
                    System.out.println("Removed: " + removed);
                    break;

                case 4:
                    System.out.print("Enter artwork ID to fetch: ");
                    int id4 = sc.nextInt();
                    try {
                        Artwork art = service.getArtworkById(id4);
                        if (art != null) {
                            System.out.println("🎨 Title: " + art.getTitle());
                        }
                    } catch (ArtWorkNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    List<Artwork> results = service.searchArtworks(keyword);
                    for (Artwork a : results) {
                        System.out.println("🔍 " + a.getTitle());
                    }
                    break;

                case 6:
                    System.out.print("Enter user ID: ");
                    int userId6 = sc.nextInt();
                    System.out.print("Enter artwork ID to add to favorites: ");
                    int artId6 = sc.nextInt();
                    boolean favAdded = service.addArtworkToFavorite(userId6, artId6);
                    System.out.println("Added to favorites: " + favAdded);
                    break;

                case 7:
                    System.out.print("Enter user ID: ");
                    int userId7 = sc.nextInt();
                    System.out.print("Enter artwork ID to remove from favorites: ");
                    int artId7 = sc.nextInt();
                    boolean favRemoved = service.removeArtworkFromFavorite(userId7, artId7);
                    System.out.println("Removed from favorites: " + favRemoved);
                    break;

                case 8:
                    System.out.print("Enter user ID: ");
                    int userId8 = sc.nextInt();
                    try {
                        List<Artwork> favorites = service.getUserFavoriteArtworks(userId8);
                        for (Artwork fav : favorites) {
                            System.out.println("❤️ " + fav.getTitle());
                        }
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 0:
                    System.out.println("Exiting... 🎨");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
