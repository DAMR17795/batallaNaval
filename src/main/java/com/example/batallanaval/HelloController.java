package com.example.batallanaval;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {


    @FXML
    private Label barco1;
    @FXML
    private Button botonEmpezar;

    private void moverBarco() {

    }

    @FXML
    public void moverBarco(Event event) {
        //X:468 Y:751

        if (barco1.getLayoutY() == 751) {
            barco1.setLayoutX(barco1.getLayoutX() + 5);
            barco1.setLayoutY(barco1.getLayoutY() - 5);
        }
        barco1.setLayoutX(barco1.getLayoutX() + 5);
        barco1.setLayoutY(barco1.getLayoutY() + 5);
        System.out.println("Posicion X: " +  barco1.getLayoutX());
        System.out.println("Posicion Y: " +  barco1.getLayoutY());
    }

    public void initialize() {

    }


}