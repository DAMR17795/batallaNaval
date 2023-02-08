package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PestañaEquipoRojo extends Stage {
    ControlJuego control;

    ProgressBar destructor;
    ProgressBar acorazado;
    ProgressBar lancha;
    ProgressBar submarino;
    Label txtVidaDestructor;
    Label txtVidaAcorazado;
    Label txtVidaLancha;
    Label txtVidaSubmarino;
    public PestañaEquipoRojo() {
        this.control = control;
        cargarInterfaz();

        destructor.progressProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0d) {
                destructor.getStyleClass().add("green-progress-bar");
            } else if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                destructor.getStyleClass().add("orange-progress-bar");

            }else {
                destructor.getStyleClass().add("red-progress-bar");
            }
        });

        acorazado.progressProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0d) {
                acorazado.getStyleClass().add("green-progress-bar");
            } else if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                acorazado.getStyleClass().add("orange-progress-bar");

            }else {
                acorazado.getStyleClass().add("red-progress-bar");
            }
        });

        submarino.progressProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0d) {
                submarino.getStyleClass().add("green-progress-bar");
            } else if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                submarino.getStyleClass().add("orange-progress-bar");

            }else {
                submarino.getStyleClass().add("red-progress-bar");
            }
        });

        lancha.progressProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0d) {
                lancha.getStyleClass().add("green-progress-bar");
            } else if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                lancha.getStyleClass().add("orange-progress-bar");

            }else {
                lancha.getStyleClass().add("red-progress-bar");
            }
        });


        Timeline moverse = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            for (Barcos barco : this.control.getBarcos()) {

                if (barco.getNombre().equals("destructor") && barco.getEquipo().equals("Rojo")) {
                    txtVidaDestructor.setText(String.valueOf(barco.getVida()));
                    destructor.setProgress((barco.getVida() / 80f));

                }

                if (barco.getNombre().equals("acorazado") && barco.getEquipo().equals("Rojo")) {
                    txtVidaAcorazado.setText(String.valueOf(barco.getVida()));
                    acorazado.setProgress((barco.getVida() / 120f));

                }

                if (barco.getNombre().equals("lancha") && barco.getEquipo().equals("Rojo")) {
                    txtVidaLancha.setText(String.valueOf(barco.getVida()));
                    lancha.setProgress((barco.getVida() / 10f));

                }

                if (barco.getNombre().equals("submarino") && barco.getEquipo().equals("Rojo")) {
                    txtVidaSubmarino.setText(String.valueOf(barco.getVida()));
                    submarino.setProgress((barco.getVida() / 30f));

                }

            }


        }));
        moverse.setCycleCount(Timeline.INDEFINITE);
        moverse.play();

    }

    public synchronized  void getControl(ControlJuego control){

        this.control = control;

    }

    private void barraPr(int vidaActual) {

        int vidaDestructor = 80;


    }

    private void cargarInterfaz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("marcadorEquipoRojo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 200, 200);

            destructor = (ProgressBar) loader.getNamespace().get("pbVidaDestructor");
            destructor.setProgress(1);
            txtVidaDestructor = (Label) loader.getNamespace().get("vidaDestructor");
            txtVidaDestructor.setText("80");

            acorazado = (ProgressBar) loader.getNamespace().get("pbVidaAcorazado");
            acorazado.setProgress(1);
            txtVidaAcorazado = (Label) loader.getNamespace().get("vidaAcorazado");
            txtVidaAcorazado.setText("120");

            lancha = (ProgressBar) loader.getNamespace().get("pbVidaLancha");
            lancha.setProgress(1);
            txtVidaLancha = (Label) loader.getNamespace().get("vidaLancha");
            txtVidaLancha.setText("10");

            submarino = (ProgressBar) loader.getNamespace().get("pbVidaSubmarino");
            submarino.setProgress(1);
            txtVidaSubmarino = (Label) loader.getNamespace().get("vidaSubmarino");
            txtVidaSubmarino.setText("30");

            destructor = (ProgressBar) loader.getNamespace().get("pbVidaDestructor");
            destructor.setProgress(1);
            txtVidaDestructor = (Label) loader.getNamespace().get("vidaDestructor");
            txtVidaDestructor.setText("80");

            setScene(scene);
            setX(0);
            setY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
