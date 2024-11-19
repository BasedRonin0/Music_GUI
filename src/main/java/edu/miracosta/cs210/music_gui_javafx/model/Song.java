package edu.miracosta.cs210.music_gui_javafx.model;

import java.io.File;
import java.io.Serializable;

public class Song implements Serializable {

    private File song;  // The .wav file for the song
    private File image; // The image (e.g., album artwork)

    // Constructor
    public Song(File song, File image) {
        this.song = song;
        this.image = image;
    }

    // Getters and setters for song and image
    public File getSong() {
        return song;
    }

    public void setSong(File song) {
        this.song = song;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    // Get the file path of the song as a string
    public String getSongPath() {
        return song.getAbsolutePath();
    }

    // Get the file path of the image as a string
    public String getImagePath() {
        return image != null ? image.getAbsolutePath() : null;  // Return null if no image is set
    }

    @Override
    public String toString() {
        // Display song's file name in the list view (you can adjust this as needed)
        return song.getName();
    }
}
