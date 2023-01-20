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

public class HelloController {


    @FXML
    private Button botonEmpezar;


    int movimientoX = 5;
    int movimientoY = 5;
    @FXML
    private Label barco1;
    @FXML
    private Label barcoAzul;
    @FXML
    private ImageView barcoImagen;

    public void movimiento(ImageView barco) {
        Timeline mover = new Timeline(new KeyFrame(Duration.seconds(0.01), (ActionEvent ae) -> {
            int numPosicion = (int) (Math.random()*4+1);


            barco.setLayoutX(barco.getLayoutX() + movimientoX);
            barco.setLayoutY(barco.getLayoutY() + movimientoY);

            int numeroAleatorio = (int) (Math.random()*2+1);


            //Cuando golpea abajo Y
            if (barco.getLayoutY() >= 751 && movimientoX == 5) {
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random()*2+1);

            }

            if (barco.getLayoutY() >= 751 && movimientoX == 5 && numeroAleatorio==2) {
                movimientoY = -5;
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutY() >= 751 && movimientoX == -5) {
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(225);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutY() >= 751 && movimientoX == -5 && numeroAleatorio==2) {
                movimientoY = -5;
                movimientoX=   5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            //Cuando golpea derecha X

            if (barcoImagen.getLayoutX() >= 988 && movimientoY == 5) {
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutX() >= 988 && movimientoY == 5 && numeroAleatorio==2) {
                movimientoX = -5;
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(225);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutX() >= 988 && movimientoY == -5) {
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(225);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutX() >= 988 && movimientoY == -5 && numeroAleatorio==2) {
                movimientoX = -5;
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            //Cuando golpea arriba Y

            if (barco.getLayoutY() <= 0 && movimientoX==-5) {
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutY() <= 0 && movimientoX==-5 && numeroAleatorio==2) {
                movimientoY = 5;
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutY() <= 0 && movimientoX== 5) {
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutY() <= 0 && movimientoX== 5 && numeroAleatorio==2) {
                movimientoY = 5;
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            //Cuando golpea izquierda X


            if (barco.getLayoutX() <= 0 && movimientoY==5) {
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutX() <= 0 && movimientoY==5 && numeroAleatorio==2) {
                movimientoX = 5;
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutX() <= 0 && movimientoY==-5) {
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco.getLayoutX() <= 0 && movimientoY==-5 && numeroAleatorio==2) {
                movimientoX = 5;
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            //System.out.println("Posicion X: " +  barco1.getLayoutX());
            //System.out.println("Posicion Y: " +  barco1.getLayoutY());

        })
        );

        mover.setCycleCount(Timeline.INDEFINITE);
        mover.play();
    }

    @FXML
    public void moverBarco(Event event) {
       movimiento(barcoImagen);
    }

    public void initialize() {
        int aleatorio = (int) (Math.random()*360+1);
        barcoImagen.setRotate(45);
    }


}