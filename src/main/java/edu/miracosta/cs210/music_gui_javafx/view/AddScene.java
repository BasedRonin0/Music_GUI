package edu.miracosta.cs210.music_gui_javafx.view;

import edu.miracosta.cs210.music_gui_javafx.controller.Controller;
import edu.miracosta.cs210.music_gui_javafx.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;

public class AddScene extends Scene {
    private Scene previousScene;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 250;
    private Controller controller = Controller.getInstance();

    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");
    private TextField titleTextField = new TextField();  // For the song title
    private Label fileLabel = new Label("No file selected");  // Display selected file name

    /**
     * In the <code>AddScene</code>, a user is able to add a new song to the collection of songs.
     * @param previousScene A reference to the previous scene to navigate back to after saving (or canceling).
     */
    public AddScene(Scene previousScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        // Song title field
        Label titleLabel = new Label("Song Title: ");
        pane.add(titleLabel, 0, 0);
        pane.add(titleTextField, 1, 0);

        // Select file button
        Button selectFileButton = new Button("Select WAV File");
        selectFileButton.setOnAction(e -> selectFile());
        pane.add(selectFileButton, 0, 1);
        pane.add(fileLabel, 1, 1);

        // Cancel and Save buttons
        cancelButton.setOnAction(e -> goBackToPrevScene());
        saveButton.setOnAction(e -> save());

        pane.add(cancelButton, 0, 2);
        pane.add(saveButton, 1, 2);

        this.setRoot(pane);
    }

    /**
     * Opens a file chooser for the user to select a .wav file.
     */
    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("WAV Files", "*.wav"));

        // Show the open file dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Display the selected file name
            fileLabel.setText("Selected: " + selectedFile.getName());
            // Store the file path as an instance variable (for use in save())
            // You might want to store this file path as part of a Song object
            fileLabel.setUserData(selectedFile.getAbsolutePath());
        }
    }

    /**
     * Saves the new song after validating the input.
     */
    private void save() {
        String title = titleTextField.getText().trim();
        String filePath = (String) fileLabel.getUserData();

        // Validate the input
        if (title.isEmpty()) {
            showAlert("Error", "Song title cannot be empty!");
            return;
        }

        if (filePath == null || filePath.isEmpty()) {
            showAlert("Error", "Please select a WAV file.");
            return;
        }

        // Create a new Song object with the selected title and file path
        Song newSong = new Song(new File(title), new File(filePath));

        // Add the new song to the collection and back-end
        controller.addItem(newSong);  // Add to the back-end
        ViewNavigator.loadScene("Player", previousScene);  // Navigate back to the player scene
    }

    /**
     * Show an alert dialog with a given title and message.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Navigates back to the previous scene without having to create a new one.
     */
    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Player", previousScene);
    }
}
