package edu.miracosta.cs210.music_gui_javafx.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;

public class Player {

    private ArrayList<Song> songs;      // List of songs in the playlist
    private MediaPlayer mediaPlayer;    // MediaPlayer to play the song
    private Song currentSong;           // The currently playing song

    // Constructor
    public Player(ArrayList<Song> songs) {
        this.songs = songs;
    }

    // Play the selected song
    public void play(Song song) {
        if (song == null) {
            System.out.println("No song selected.");
            return;
        }

        // Stop the current song if any
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        // Create a new Media object for the selected song file
        File songFile = song.getSong();
        if (songFile.exists()) {
            Media media = new Media(songFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();  // Play the selected song
            currentSong = song;  // Set this song as the current song
            System.out.println("Playing: " + song.getSong().getName());
        } else {
            System.out.println("The selected song file does not exist.");
        }
    }

    // Pause the current song
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            System.out.println("Song paused.");
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    // Reset the current song to the beginning
    public void reset() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.ZERO);  // Reset to the beginning of the song
            mediaPlayer.play();  // Resume playback
            System.out.println("Song reset to the beginning.");
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    // Stop the current song
    public void close() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();  // Stop playback
            mediaPlayer = null;  // Release the mediaPlayer object
            currentSong = null;  // Clear the reference to the current song
            System.out.println("Song stopped.");
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    // Get the list of songs
    public ArrayList<Song> getSongs() {
        return songs;
    }

    // Get the currently playing song
    public Song getCurrentSong() {
        return currentSong;
    }
}
