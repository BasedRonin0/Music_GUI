package edu.miracosta.cs210.music_gui_javafx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {

    public static final String BINARY_FILE = "songs.dat";
    public static boolean binaryFileHasData() {
        File binaryFile = new File(BINARY_FILE);
        return binaryFile.exists() && binaryFile.length() > 0;
    }

    public static ObservableList<Song> populateListFromBinaryFile() {
        ObservableList<Song> allItemsList = FXCollections.observableArrayList();
        try	{
            ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
            // Make a temp array
            Song[] temp = (Song[]) fileReader.readObject();
            allItemsList.addAll(temp);
            fileReader.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return allItemsList;
    }

    public static boolean writeDataToBinary(ObservableList<Song> allItemsList) {
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
            Song[] temp = new Song[allItemsList.size()];
            allItemsList.toArray(temp);
            fileWriter.writeObject(temp);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
