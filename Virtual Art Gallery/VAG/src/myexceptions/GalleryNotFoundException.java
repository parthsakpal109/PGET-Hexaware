package myexceptions;

public class GalleryNotFoundException extends Exception {
    public GalleryNotFoundException() {
        super("Gallery not found.");
    }

    public GalleryNotFoundException(String message) {
        super(message);
    }
}
