package edu.miracosta.cs210.music_gui_javafx.view;

import javafx.scene.Scene;
import edu.miracosta.cs210.music_gui_javafx.controller.Controller;
import edu.miracosta.cs210.music_gui_javafx.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PlayerScene extends Scene {
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    private ComboBox<String> typesCB;
    private ListView<Song> songsLV = new ListView<>();
    private Button addButton = new Button("+ Add Song");
    private Button removeButton = new Button("- Remove Song");
    private Controller controller = Controller.getInstance();
    private ObservableList<Song> songsList;
    private Song selectedSong;


    public PlayerScene() {
        super(new GridPane(), WIDTH, HEIGHT);
        songsList = controller.getAllItems();
        songsLV.setItems(songsList);
        songsLV.setPrefWidth(WIDTH);
        songsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedItem(newVal));
        ObservableList<String> types = FXCollections.observableArrayList("", "Moving Company", "Gym", "Video Game", "Restaurant", "Movie", "Car");

        addButton.setOnAction(e -> addSong());
        removeButton.setDisable(true);
        removeButton.setOnAction(e -> removeSong());

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Type:"), 0, 1);
        pane.add(typesCB, 1, 1);


        pane.add(addButton, 0, 5, 2, 1);
        pane.add(songsLV, 0, 6, 2, 1);
        pane.add(removeButton, 0, 7, 2, 1);

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
        // Otherwise, remove the selected item from the back-end first, then the front-end
        controller.removeItem(selectedSong);  // back-end, from the Model (binary file)
        songsList.remove(selectedSong);  // visually, from the View

        //DONE: and update the list view to select -1 (nothing)
        songsLV.getSelectionModel().select(-1);
    }

    /**
     * Allows the user to add a new item by navigating to the AddScene.
     */
    private void addSong() {
        // Use the ViewNavigator to load the AddScene
        ViewNavigator.loadScene("Add Review", new AddScene(this));
    }
}
