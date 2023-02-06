package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MoverBarcos {
    int movimientoX = 5;
    int movimientoY = 5;

    public void mover(ImageView barco) {

        int maximoY = 751;
        int maximoX = 988;

        Timeline mover = new Timeline(new KeyFrame(Duration.seconds(0.01), (ActionEvent ae) -> {

            int numPosicion = (int) (Math.random() * 4 + 1);

            barco.setLayoutX(barco.getLayoutX() + movimientoX);
            barco.setLayoutY(barco.getLayoutY() + movimientoY);

            int numeroAleatorio = (int) (Math.random() * 2 + 1);

            //Cuando golpea abajo Y
            if (barco.getLayoutY() >= maximoY && movimientoX == 5) {
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutY() >= maximoY && movimientoX == 5 && numeroAleatorio == 2) {
                movimientoY = -5;
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutY() >= maximoY && movimientoX == -5) {
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(225);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutY() >= maximoY && movimientoX == -5 && numeroAleatorio == 2) {
                movimientoY = -5;
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            //Cuando golpea derecha X

            if (barco.getLayoutX() >= maximoX && movimientoY == 5) {
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutX() >= maximoX && movimientoY == 5 && numeroAleatorio == 2) {
                movimientoX = -5;
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(225);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutX() >= maximoX && movimientoY == -5) {
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(225);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutX() >= maximoX && movimientoY == -5 && numeroAleatorio == 2) {
                movimientoX = -5;
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            //Cuando golpea arriba Y

            if (barco.getLayoutY() <= 0 && movimientoX == -5) {
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutY() <= 0 && movimientoX == -5 && numeroAleatorio == 2) {
                movimientoY = 5;
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutY() <= 0 && movimientoX == 5) {
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutY() <= 0 && movimientoX == 5 && numeroAleatorio == 2) {
                movimientoY = 5;
                movimientoX = -5;
                barco.setRotate(0);
                barco.setRotate(135);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            //Cuando golpea izquierda X


            if (barco.getLayoutX() <= 0 && movimientoY == 5) {
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutX() <= 0 && movimientoY == 5 && numeroAleatorio == 2) {
                movimientoX = 5;
                movimientoY = -5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutX() <= 0 && movimientoY == -5) {
                movimientoX = 5;
                barco.setRotate(0);
                barco.setRotate(315);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

            if (barco.getLayoutX() <= 0 && movimientoY == -5 && numeroAleatorio == 2) {
                movimientoX = 5;
                movimientoY = 5;
                barco.setRotate(0);
                barco.setRotate(45);
                numeroAleatorio = (int) (Math.random() * 2 + 1);
            }

        })
        );

        mover.setCycleCount(Timeline.INDEFINITE);
        mover.play();
    }
}
