package com.example.jude.musicstructureapp;

public class Song {

    private String songName;
    private String songAuthor;
    private int imageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Song(String songName, String songAuthor, int imageResourceId) {
        this.songName = songName;
        this.songAuthor = songAuthor;
        this.imageResourceId = imageResourceId;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }
}
