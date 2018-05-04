package com.example.jude.musicstructureapp;

public class Author {

    private String authorName;
    private String authorSurname;
    private String numberOfAlbums;
    private int imageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Author(String authorName, String authorSurname, String numberOfAlbums, int imageResourceId) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.numberOfAlbums = numberOfAlbums;
        this.imageResourceId = imageResourceId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public String getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }
}
