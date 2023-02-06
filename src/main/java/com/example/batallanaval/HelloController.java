package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController extends MoverBarcos {


    @FXML
    private Button botonEmpezar;


    int movimientoX = 5;
    int movimientoY = 5;
    @FXML
    private ImageView barcoRojo;
    @FXML
    private ImageView barcoAzul;



    @FXML
    public void moverBarco(Event event) {
        mover(barcoRojo);
        //mover(barcoAzul);
    }

    public void initialize() {
        int aleatorio = (int) (Math.random()*360+1);
        barcoRojo.setRotate(225+180);
        barcoAzul.setRotate(135);
    }


}