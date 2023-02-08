package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class ControlJuego {
    ArrayList<Barcos> barcos;
    String winner = "";
    DialogPane dialog;

    MediaPlayer mediaPlayer;

    public synchronized ArrayList<Barcos> getBarcos() {
        return barcos;
    }

    public ControlJuego() {
        barcos = new ArrayList<Barcos>();
        dialog = new DialogPane();
    }

    public synchronized void addBarco(Barcos barco) {
        barcos.add(barco);
    }

    Timeline ganador;

    boolean gameover = false;

    public void ganador() {

        ganador = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {

            int barcosAzules = 0;
            int barcosRojos = 0;
            for (Barcos barco : barcos) {

                if (barco.getVida() > 0) {

                    if (barco.getEquipo().equals("Azul")) {

                        barcosAzules++;
                    }

                    if (barco.getEquipo().equals("Rojo")) {

                        barcosRojos++;
                    }

                }


            }
            if (barcosAzules == 0 && barcosRojos >= 1) {
                winner = "Rojo";
                mostrarEquipoGanador(winner);
                ganador.stop();
            }

            if (barcosRojos == 0 && barcosAzules >= 1) {
                winner = "Azul";
                mostrarEquipoGanador(winner);
                ganador.stop();
            }


        }));
        ganador.setCycleCount(Timeline.INDEFINITE);
        ganador.play();

        ganador.setOnFinished(o -> {
            System.out.println("Ganador: " + winner);
            System.exit(0);


        });


    }


    public void mostrarEquipoGanador(String nombreEquipo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Equipo ganador");
        alert.setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("imagenes/iconoApp.png").toString()));


        Platform.runLater(() -> {

            dialog = alert.getDialogPane();

            if (nombreEquipo.equals("Azul")) {
                dialog.getStylesheets().add(this.getClass().getResource("CSS/DialogoWinnerEspana.css").toString());
                ImageView imageView = new ImageView(new Image(this.getClass().getResource("imagenes/banderaEspana.png").toString()));
                imageView.setFitHeight(70);
                imageView.setFitWidth(80);
                dialog.setGraphic(imageView);
                Media pick = new Media(this.getClass().getResource("musica/espanaWin.mp3").toString());
                mediaPlayer= new MediaPlayer(pick);
                mediaPlayer.play();


            } else {
                dialog.getStylesheets().add(this.getClass().getResource("CSS/DialogoWinnerFrancia.css").toString());
                dialog.getStyleClass().add("dialog");
                ImageView imageView = new ImageView(new Image(this.getClass().getResource("imagenes/banderaFrancia.png").toString()));
                imageView.setFitHeight(70);
                imageView.setFitWidth(80);
                dialog.setGraphic(imageView);
                Media pick = new Media(this.getClass().getResource("musica/franciaWin.mp3").toString());
                mediaPlayer= new MediaPlayer(pick);
                mediaPlayer.play();
            }

            HelloApplication ha = new HelloApplication();
            ha.mediaPlayerPause().stop();

            dialog.getStyleClass().add("dialog");
            alert.setContentText("El equipo ganador es: " + nombreEquipo);
            alert.showAndWait().ifPresent(response -> System.exit(0));

        });


    }

}
