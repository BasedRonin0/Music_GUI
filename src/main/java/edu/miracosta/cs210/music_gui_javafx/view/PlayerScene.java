package edu.miracosta.cs210.music_gui_javafx.view;

import javafx.scene.Scene;
import edu.miracosta.cs210.music_gui_javafx.controller.Controller;
import edu.miracosta.cs210.music_gui_javafx.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class PlayerScene extends Scene {
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;

    private ListView<Song> songsLV = new ListView<>();
    private Button addButton = new Button("+ Add Song");
    private Button removeButton = new Button("- Remove Song");
    private Button playButton = new Button("Play");
    private Button pauseButton = new Button("Pause");
    private Button stopButton = new Button("Stop");
    private Controller controller = Controller.getInstance();
    private ObservableList<Song> songsList;
    private Song selectedSong;

    private MediaPlayer mediaPlayer;  // MediaPlayer to play the audio

    public PlayerScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        // Get all songs from the controller and set them in the ListView
        songsList = controller.getAllItems();
        songsLV.setItems(songsList);
        songsLV.setPrefWidth(WIDTH);
        songsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedItem(newVal));

        addButton.setOnAction(e -> addSong());
        removeButton.setDisable(true);
        removeButton.setOnAction(e -> removeSong());

        playButton.setOnAction(e -> playSelectedSong());
        pauseButton.setOnAction(e -> pauseSong());
        stopButton.setOnAction(e -> stopSong());

        // Layout setup
        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(addButton, 0, 5, 2, 1);
        pane.add(songsLV, 0, 6, 2, 1);
        pane.add(removeButton, 0, 7, 2, 1);
        pane.add(playButton, 0, 8);
        pane.add(pauseButton, 1, 8);
        pane.add(stopButton, 0, 9, 2, 1);

        this.setRoot(pane);
    }

    private void updateSelectedItem(Song newVal) {
        selectedSong = newVal;
        // Disable the removeButton if selectedItem is null
        removeButton.setDisable(selectedSong == null);
    }

    private void removeSong() {
        // If the selected item is null, just return
        if (selectedSong == null)
            return;
        // Remove the selected item from the back-end (Model) and front-end (View)
        controller.removeItem(selectedSong);  // Back-end removal
        songsList.remove(selectedSong);  // Visually remove the song from ListView

        // Update the ListView to select nothing
        songsLV.getSelectionModel().select(-1);
    }

    /**
     * Allows the user to add a new item by navigating to the AddScene.
     */
    private void addSong() {
        // Use the ViewNavigator to load the AddScene
        ViewNavigator.loadScene("Add Song", new AddScene(this));
    }

    private void playSelectedSong() {
        if (selectedSong != null) {
            // Stop any currently playing song
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // Get the file and check if it exists
            File songFile = selectedSong.getSong();
            if (!songFile.exists()) {
                System.out.println("File not found: " + songFile.getAbsolutePath());
                return;
            }

            // Create a new Media object with the selected song
            String songPath = songFile.toURI().toString();
            System.out.println("Song path: " + songPath); // Debugging output

            Media media = new Media(songPath);

            // Error handling for media player
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnError(() -> {
                System.out.println("Error playing media: " + mediaPlayer.getError().getMessage());
            });

            mediaPlayer.play();
            System.out.println("Playing: " + selectedSong.getSong().getName());
        }
    }

    private void pauseSong() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            System.out.println("Paused: " + selectedSong.getSong().getName());
        }
    }

    private void stopSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            System.out.println("Stopped: " + selectedSong.getSong().getName());
        }
    }
}
