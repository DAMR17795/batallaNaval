package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
import java.io.IOException;
import java.util.ArrayList;

import static com.example.batallanaval.HelloApplication.mediaPlayer2;

public class ControlJuego {
    MediaPlayer mediaPlayer;
    String nombreGanador = "";
    Timeline ganador;
    DialogPane dialog;
    ArrayList<Barcos> barcos;

    /**
     * Constructor del control de juego
     */
    public ControlJuego() {
        barcos = new ArrayList<Barcos>();
        dialog = new DialogPane();
    }


    /**
     * Método que recorre el arrayList de barcos
     * creados para ver que equipo ha ganado.
     * Obtiene el nombred del equipo de cada
     * objeto de Barcos, si el numero de Azul
     * o Rojo es mayor que el otro, habrá ganado
     * ese equipo
     */
    public void gameOver() {
        ganador = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            int barcosAzules = 0;
            int barcosRojos = 0;

            for (Barcos barco : barcos) {
                if (barco.getVidaBarco() > 0) {
                    if (barco.getNombreEquipo().equals("Azul")) {
                        barcosAzules++;
                    }
                    if (barco.getNombreEquipo().equals("Rojo")) {
                        barcosRojos++;
                    }
                }
            }
            //Muestra el equipo ganador
            if (barcosAzules == 0 && barcosRojos >= 1) {
                nombreGanador = "Rojo";
                mostrarEquipoGanador(nombreGanador);
                ganador.stop();
            }
            if (barcosRojos == 0 && barcosAzules >= 1) {
                nombreGanador = "Azul";
                mostrarEquipoGanador(nombreGanador);
                ganador.stop();
            }
        }));
        ganador.setCycleCount(Timeline.INDEFINITE);
        ganador.play();
    }


    /**
     * Método para abrir un dialog, mostrando el equipo
     * que ha ganado
     * @param nombreEquipo -> nombre del Equipo que ha ganado
     */
    public void mostrarEquipoGanador(String nombreEquipo) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("imagenes/iconoApp.png").toString()));

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
        Platform.runLater(() -> {

            dialog = alert.getDialogPane();

            if (nombreEquipo.equals("Azul")) {
                dialog.getStylesheets().add(this.getClass().getResource("CSS/ganadorAzul.css").toString());
                ImageView imageView = new ImageView(new Image(this.getClass().getResource("imagenes/imagenPremio.png").toString()));
                imageView.setFitHeight(70);
                imageView.setFitWidth(80);
                dialog.setGraphic(imageView);
                Media pick = new Media(this.getClass().getResource("musica/cancionVictoria.mp3").toString());
                mediaPlayer= new MediaPlayer(pick);
                mediaPlayer.play();
                alert.setTitle("Victoria del Equipo " + nombreEquipo);


            } else {
                dialog.getStylesheets().add(this.getClass().getResource("CSS/ganadorRojo.css").toString());
                dialog.getStyleClass().add("dialog");
                ImageView imageView = new ImageView(new Image(this.getClass().getResource("imagenes/imagenPremio.png").toString()));
                imageView.setFitHeight(70);
                imageView.setFitWidth(80);
                dialog.setGraphic(imageView);
                Media pick = new Media(this.getClass().getResource("musica/cancionVictoria.mp3").toString());
                mediaPlayer= new MediaPlayer(pick);
                mediaPlayer.play();
                alert.setTitle("Victoria del Equipo " + nombreEquipo);
            }

            //Paramos música de inicio
            mediaPlayer2.stop();

            dialog.getStyleClass().add("dialog");
            alert.setContentText("¡El Equipo " + nombreEquipo + " es el ganador!");
            Inicio inicio = new Inicio();
            alert.showAndWait().ifPresent(response -> {
                try {
                    mediaPlayer.stop();
                    inicio.start(new Stage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    });
    pause.play();
    }

    /**
     * Método para añadir barco al arrayList que
     * servirá para comprobar que equipo ha ganado
     * @param barco -> barco que se añade al arrayList
     */
    public synchronized void addBarco(Barcos barco) {
        barcos.add(barco);
    }

    /**
     * Método que devuelve el arrayList de barcos
     * @return
     */
    public synchronized ArrayList<Barcos> getBarcos() {
        return barcos;
    }
}
