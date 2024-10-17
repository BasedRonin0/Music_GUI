package edu.miracosta.cs210.music_gui_javafx.view;


import edu.miracosta.cs210.music_gui_javafx.controller.Controller;
import edu.miracosta.cs210.music_gui_javafx.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOError;

public class AddScene extends Scene {
    private Scene previousScene;


    public static final int WIDTH = 500;
    public static final int HEIGHT = 250;
    private Controller controller = Controller.getInstance();



    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");


    /**
     * In the <code>AddScene</code>, a user is able to add a new Vehicle to the collection of reviews.
     * All fields are required (error message will appear if not provided)
     * @param previousScene A reference to the MainScene so that after saving (or canceling), user
     *                  is returned back to the main scene.
     */
    public AddScene(Scene previousScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;


        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));






        pane.add(cancelButton, 0, 6);
        pane.add(saveButton, 1, 6);


        saveButton.setOnAction(e -> save());
        cancelButton.setOnAction(e -> goBackToPrevScene());
        this.setRoot(pane);
    }

    private void save() {


    }



    /**
     * Navigates back to the previous scene without having to create a new one.
     */
    private void goBackToPrevScene() {
        //DONE: Navigate back to the previous scene (e.g. MainScene)
        ViewNavigator.loadScene("Player", previousScene);
    }
    private void removeNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {

        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                gridPane.getChildren().remove(node);
                break;
            }
        }
    }
}
