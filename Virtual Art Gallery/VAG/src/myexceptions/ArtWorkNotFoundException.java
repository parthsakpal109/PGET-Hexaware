package myexceptions;

public class ArtWorkNotFoundException extends Exception {
    public ArtWorkNotFoundException() {
        super("Artwork not found.");
    }

    public ArtWorkNotFoundException(String message) {
        super(message);
    }
}
