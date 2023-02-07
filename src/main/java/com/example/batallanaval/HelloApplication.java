package com.example.batallanaval;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    MediaPlayer mediaPlayer2;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Batalla Naval");
        stage.setY(0);
        stage.setX(255);

        stage.setResizable(false);
        Media pick2 = new Media(this.getClass().getResource("musica/cancionBatallaMenos.mp3").toString());
        mediaPlayer2 = new MediaPlayer(pick2);
        mediaPlayer2.setVolume(100000);
        mediaPlayer2.play();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}