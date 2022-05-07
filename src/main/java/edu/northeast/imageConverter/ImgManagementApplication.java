package edu.northeast.imageConverter;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImgManagementApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ImgManagementApplication.class.getResource("img-panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Image Converter!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}