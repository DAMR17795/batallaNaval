package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
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
    ImageView destructorDisImg;
    ImageView acorazadoDisImg;
    ImageView submarinoDisImg;
    ImageView lanchaDisImg;

    public PestañaEquipoRojo() {
        cargarInterfaz();

        destructor.progressProperty().addListener((obs, oldValue, newValue) -> {
             if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                destructor.getStyleClass().add("orange-progress-bar");

            }else {
                destructor.getStyleClass().add("red-progress-bar");
            }
        });

        acorazado.progressProperty().addListener((obs, oldValue, newValue) -> {
             if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                acorazado.getStyleClass().add("orange-progress-bar");

            }else {
                acorazado.getStyleClass().add("red-progress-bar");
            }
        });

        submarino.progressProperty().addListener((obs, oldValue, newValue) -> {
             if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                submarino.getStyleClass().add("orange-progress-bar");

            }else {
                submarino.getStyleClass().add("red-progress-bar");
            }
        });

        lancha.progressProperty().addListener((obs, oldValue, newValue) -> {
             if (newValue.doubleValue() <=0.99d && newValue.doubleValue() >=0.50d) {

                lancha.getStyleClass().add("orange-progress-bar");

            }else {
                lancha.getStyleClass().add("red-progress-bar");
            }
        });


        Timeline moverse = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            for (Barcos barco : this.control.getBarcos()) {

                if (barco.getNombreBarco().equals("destructor") && barco.getNombreEquipo().equals("Rojo")) {
                    txtVidaDestructor.setText(String.valueOf(barco.getVidaBarco()));
                    destructor.setProgress((barco.getVidaBarco() / 80f));
                    if (barco.recargando() && barco.getVidaBarco() > 0) {
                        destructorDisImg.setVisible(true);
                    } else {
                        destructorDisImg.setVisible(false);
                    }

                }

                if (barco.getNombreBarco().equals("acorazado") && barco.getNombreEquipo().equals("Rojo")) {
                    txtVidaAcorazado.setText(String.valueOf(barco.getVidaBarco()));
                    acorazado.setProgress((barco.getVidaBarco() / 120f));
                    if (barco.recargando() && barco.getVidaBarco() > 0) {
                        acorazadoDisImg.setVisible(true);
                    } else {
                        acorazadoDisImg.setVisible(false);
                    }

                }

                if (barco.getNombreBarco().equals("lancha") && barco.getNombreEquipo().equals("Rojo")) {
                    txtVidaLancha.setText(String.valueOf(barco.getVidaBarco()));
                    lancha.setProgress((barco.getVidaBarco() / 10f));
                    if (barco.recargando() && barco.getVidaBarco() > 0) {
                        lanchaDisImg.setVisible(true);
                    } else {
                        lanchaDisImg.setVisible(false);
                    }

                }

                if (barco.getNombreBarco().equals("submarino") && barco.getNombreEquipo().equals("Rojo")) {
                    txtVidaSubmarino.setText(String.valueOf(barco.getVidaBarco()));
                    submarino.setProgress((barco.getVidaBarco() / 30f));
                    if (barco.recargando() && barco.getVidaBarco() > 0) {
                        submarinoDisImg.setVisible(true);
                    } else {
                        submarinoDisImg.setVisible(false);
                    }

                }

            }


        }));
        moverse.setCycleCount(Timeline.INDEFINITE);
        moverse.play();

    }

    private void cargarInterfaz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("marcadorEquipoRojo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 200, 200);

            destructor = (ProgressBar) loader.getNamespace().get("pbDestructor");
            destructor.setProgress(1);
            txtVidaDestructor = (Label) loader.getNamespace().get("indicadorDestructor");
            txtVidaDestructor.setText("80");

            acorazado = (ProgressBar) loader.getNamespace().get("pbAcorazado");
            acorazado.setProgress(1);
            txtVidaAcorazado = (Label) loader.getNamespace().get("indicadorAcorazado");
            txtVidaAcorazado.setText("120");

            lancha = (ProgressBar) loader.getNamespace().get("pbLancha");
            lancha.setProgress(1);
            txtVidaLancha = (Label) loader.getNamespace().get("indicadorLancha");
            txtVidaLancha.setText("10");

            submarino = (ProgressBar) loader.getNamespace().get("pbSubmarino");
            submarino.setProgress(1);
            txtVidaSubmarino = (Label) loader.getNamespace().get("indicadorSubmarino");
            txtVidaSubmarino.setText("30");

            destructor = (ProgressBar) loader.getNamespace().get("pbDestructor");
            destructor.setProgress(1);
            txtVidaDestructor = (Label) loader.getNamespace().get("indicadorDestructor");
            txtVidaDestructor.setText("80");

            destructorDisImg = (ImageView) loader.getNamespace().get("recargaDestructor");
            lanchaDisImg = (ImageView) loader.getNamespace().get("recargaLancha");
            acorazadoDisImg = (ImageView) loader.getNamespace().get("recargaAcorazado");
            submarinoDisImg = (ImageView) loader.getNamespace().get("recargaSubmarino");

            setScene(scene);
            setX(0);
            setY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void getControl(ControlJuego control){
        this.control = control;
    }
}
