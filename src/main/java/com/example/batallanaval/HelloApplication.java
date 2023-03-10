package com.example.batallanaval;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static MediaPlayer mediaPlayer2;

    /**
     * Ventana inicial que comienza el juego,
     * cargando el escenario de la vista y
     * la música inicial
     * @param stage -> escenario
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 992);
        stage.setTitle("Batalla Naval - Daniel Martín Romero");
        stage.getIcons().add(new Image(this.getClass().getResource("imagenes/iconoApp.png").toString()));
        stage.setY(0);
        stage.setX(500);

        stage.setResizable(false);
        stage.centerOnScreen();

        //Musica de combate
        Platform.runLater(() -> {
            Media pick2 = new Media(this.getClass().getResource("musica/cancionBatalla.mp3").toString());
            mediaPlayer2 = new MediaPlayer(pick2);
            mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer2.setVolume(0.5);
            mediaPlayer2.play();
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}