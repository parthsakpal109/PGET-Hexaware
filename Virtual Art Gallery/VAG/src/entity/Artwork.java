package entity;

import java.sql.Date;

public class Artwork {
    private int id;  // Make sure there's an ID field
    private String title;
    private String description;
    private Date creationDate;
    private String medium;
    private String imageUrl;
    private int artistId;

    // Constructor
    public Artwork(int id, String title, String description, Date creationDate, String medium, String imageUrl, int artistId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.medium = medium;
        this.imageUrl = imageUrl;
        this.artistId = artistId;
    }

    // Getters and Setters

    public int getId() {
        return id;  // Ensure there's a method to get the artwork's ID
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
