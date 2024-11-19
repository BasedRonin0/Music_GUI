package edu.miracosta.cs210.music_gui_javafx.model;

import java.util.ArrayList;

public class Player {

    private ArrayList<Song> songs;

    public Player(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void play() {};
    public void pause() {};
    public void reset() {};
    public void close() {};
}
