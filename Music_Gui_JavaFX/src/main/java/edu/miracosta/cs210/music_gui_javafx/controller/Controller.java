package edu.miracosta.cs210.music_gui_javafx.controller;

import edu.miracosta.cs210.music_gui_javafx.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class Controller {

    private ObservableList<Song> allSongsList;

    // Singleton
    private static Controller theController;
    private Controller() {}
    public static Controller getInstance() {
        // check to see if the controller is null
        if (theController == null) {
            theController = new Controller();
            // Decide whether to pull from binary or csv
            if (Model.binaryFileHasData()) {
                theController.allSongsList = Model.populateListFromBinaryFile();
            }
        }
        return theController;
    }

    public ObservableList<Song> getAllItems() {
        return allSongsList;
    }

    public void saveData() {
        Model.writeDataToBinary(allSongsList);
    }

    public void addItem(Song s)
    {
        allSongsList.add(s);
    }
    public void removeItem(Song selectedSong) {
        allSongsList.remove(selectedSong);
    }
}
