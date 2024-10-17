package edu.miracosta.cs210.music_gui_javafx.view;

import edu.miracosta.cs210.music_gui_javafx.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ViewNavigator.setStage(stage);
        ViewNavigator.loadScene("Player", new PlayerScene());
    }

    public void stop() throws Exception {
        Controller.getInstance().saveData();
    }

    public static void main(String[] args) {
        launch();
    }
}
