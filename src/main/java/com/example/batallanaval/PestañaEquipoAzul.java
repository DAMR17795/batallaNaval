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

public class PestañaEquipoAzul extends Stage {
    ControlJuego control;
    ProgressBar destructor;
    ProgressBar acorazado;
    ProgressBar lancha;
    ProgressBar submarino;
    Label txtVidaDestructor;
    Label txtVidaAcorazado;
    Label txtVidaLancha;
    Label txtVidaSubmarino;

    public PestañaEquipoAzul() {
        this.control = control;
        cargarInterfaz();


        Timeline moverse = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            for (Barcos barco : this.control.getBarcos()) {

                if (barco.getNombre().equals("destructor") && barco.getEquipo().equals("Azul")) {
                    txtVidaDestructor.setText(String.valueOf(barco.getVida()));
                    destructor.setProgress((barco.getVida() / 80f));

                }

                if (barco.getNombre().equals("acorazado") && barco.getEquipo().equals("Azul")) {
                    txtVidaAcorazado.setText(String.valueOf(barco.getVida()));
                    acorazado.setProgress((barco.getVida() / 120f));
                }

                if (barco.getNombre().equals("lancha") && barco.getEquipo().equals("Azul")) {
                    txtVidaLancha.setText(String.valueOf(barco.getVida()));
                    lancha.setProgress((barco.getVida() / 10f));
                }

                if (barco.getNombre().equals("submarino") && barco.getEquipo().equals("Azul")) {
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


    private void cargarInterfaz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("marcadorEquipoAzul.fxml"));
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
            setX(1279);
            setY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
