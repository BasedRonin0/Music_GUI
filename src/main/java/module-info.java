module edu.miracosta.cs210.music_gui_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens edu.miracosta.cs210.music_gui_javafx to javafx.fxml;
    exports edu.miracosta.cs210.music_gui_javafx.controller;
    opens edu.miracosta.cs210.music_gui_javafx.controller to javafx.fxml;
    exports edu.miracosta.cs210.music_gui_javafx.view;
    opens edu.miracosta.cs210.music_gui_javafx.view to javafx.fxml;
}