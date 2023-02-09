package com.example.batallanaval;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InicioController {
    MediaPlayer mediaPlayer;
    @javafx.fxml.FXML
    private Button btComenzar;
    @javafx.fxml.FXML
    private Button btSalir;

    @javafx.fxml.FXML
    public void comenzarJuego(Event event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mediaPlayer.stop();
        stage.close();

        HelloApplication ha = new HelloApplication();
        ha.start(new Stage());
    }

    @javafx.fxml.FXML
    public void salirJuego(Event event) {
        System.exit(0);
    }

    @javafx.fxml.FXML
    public void initialize() {
        Platform.runLater(() -> {
            Stage currentStage = (Stage) btSalir.getScene().getWindow();

            for (Window window : Window.getWindows()) {
                if (window instanceof Stage && window != currentStage) {
                    ((Stage) window).close();
                }
            }
            Media pick = new Media(this.getClass().getResource("musica/cancionBatalla.mp3").toString());
            mediaPlayer = new MediaPlayer(pick);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.37);
            mediaPlayer.play();
        });

    }
}
