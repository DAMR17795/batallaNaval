package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class HelloController {


    @FXML
    private Label barco1;
    @FXML
    private Button botonEmpezar;

    private void moverBarco() {

    }

    int movimientoX = 5;
    int movimientoY = 5;

    @FXML
    public void moverBarco(Event event) {
        //X:988 Y:751

        Timeline mover = new Timeline(new KeyFrame(Duration.seconds(0.01), (ActionEvent ae) -> {
            barco1.setLayoutX(barco1.getLayoutX() + movimientoX);
            barco1.setLayoutY(barco1.getLayoutY() + movimientoY);

            int numeroAleatorio = (int) (Math.random()*2+1);


            //Cuando golpea abajo Y
            if (barco1.getLayoutY() >= 751 && movimientoX == 5) {
                System.out.println("Numero aleatorio: " + numeroAleatorio);
                movimientoY = -5;
                barco1.setRotate(0);
                barco1.setRotate(315);
                numeroAleatorio = (int) (Math.random()*2+1);

            }

            if (barco1.getLayoutY() >= 751 && movimientoX == 5 && numeroAleatorio==2) {
                System.out.println("Numero aleatorio: " + numeroAleatorio);
                movimientoY = -5;
                movimientoX = -5;
                barco1.setRotate(0);
                barco1.setRotate(135);
                numeroAleatorio = (int) (Math.random()*2+1);
            }

            if (barco1.getLayoutY() >= 751 && movimientoX == -5) {
                movimientoY = -5;
                barco1.setRotate(0);
                barco1.setRotate(225);
            }

            //Cuando golpea derecha X

            if (barco1.getLayoutX() >= 988 && movimientoY == 5) {
                movimientoX = -5;
                barco1.setRotate(0);
                barco1.setRotate(135);
            }

            if (barco1.getLayoutX() >= 988 && movimientoY == -5) {
                movimientoX = -5;
                barco1.setRotate(0);
                barco1.setRotate(225);
            }

            //Cuando golpea arriba Y

            if (barco1.getLayoutY() <= 0 && movimientoX==-5) {
                movimientoY = 5;
                barco1.setRotate(0);
                barco1.setRotate(135);
            }

            if (barco1.getLayoutY() <= 0 && movimientoX== 5) {
                movimientoY = 5;
                barco1.setRotate(0);
                barco1.setRotate(45);
            }

            //Cuando golpea izquierda X


            if (barco1.getLayoutX() <= 0 && movimientoY==5) {
                movimientoX = 5;
                barco1.setRotate(0);
                barco1.setRotate(45);
            }

            if (barco1.getLayoutX() <= 0 && movimientoY==-5) {
                movimientoX = 5;
                barco1.setRotate(0);
                barco1.setRotate(315);
            }

            //System.out.println("Posicion X: " +  barco1.getLayoutX());
            //System.out.println("Posicion Y: " +  barco1.getLayoutY());

        })
        );

        mover.setCycleCount(Timeline.INDEFINITE);
        mover.play();

    }

    public void initialize() {
        barco1.setRotate(45);
    }


}