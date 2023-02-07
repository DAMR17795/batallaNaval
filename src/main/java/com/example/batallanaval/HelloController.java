package com.example.batallanaval;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloController {


    private static boolean terminarJuego = false;
    int x, y;


    boolean pito = true;

    Barcos barcoDesEsp;
    Barcos barcoLanEsp;
    Barcos barcoAcoEsp;
    Barcos barcoSubEsp;
    Barcos barcoDesFr;
    Barcos barcoLanFr;
    Barcos barcoAcoFr;
    Barcos barcoSubFr;
    ControlJuego control;
    private Image fondo;
    @FXML
    private AnchorPane ventana;

    public void initialize() {

        Image fondo = new Image(getClass().getResourceAsStream("imagenes/fondo.png"));
        ImageView back = new ImageView(fondo);


        ventana.setBackground(new Background(new BackgroundImage(back.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        creacionBarcos();
    }
    List<Integer> numbers = new ArrayList<>();

    public void asignarPos(ImageView imagen, int num) {

        if (num == 1) {

            imagen.setLayoutX(28);
            imagen.setLayoutY(371);

        }

        if (num == 2) {

            imagen.setLayoutX(28);
            imagen.setLayoutY(75);

        }

        if (num == 3) {
            imagen.setLayoutX(28);
            imagen.setLayoutY(149);

        }

        if (num == 4) {

            imagen.setLayoutX(28);
            imagen.setLayoutY(575);
        }

    }

    public void asignarPosFr(ImageView imagen, int num) {

        if (num == 1) {

            imagen.setLayoutX(882);
            imagen.setLayoutY(371);

        }

        if (num == 2) {

            imagen.setLayoutX(876);
            imagen.setLayoutY(75);

        }

        if (num == 3) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(147);

        }

        if (num == 4) {

            imagen.setLayoutX(876);
            imagen.setLayoutY(575);
        }

    }

    public void creacionBarcos() {

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        Collections.shuffle(numbers);
        control = new ControlJuego();

        ImageView bola = new ImageView(new Image((getClass().getResourceAsStream("imagenes/cannonball.png"))));
        bola.setFitWidth(30);
        bola.setFitHeight(30);

        ImageView destructorImg = new ImageView();
        destructorImg.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorAzul.png")));
        asignarPos(destructorImg, numbers.remove(0));
        control.addBarco(barcoDesEsp = new Barcos("destructor", "Azul", destructorImg, control.getBarcos(), bola, ventana));


        ImageView acorazadoImg = new ImageView();
        acorazadoImg.setImage(new Image(getClass().getResourceAsStream("imagenes/acorazadoAzul.png")));
        asignarPos(acorazadoImg, numbers.remove(0));
        control.addBarco(barcoAcoEsp = new Barcos("acorazado", "Azul", acorazadoImg, control.getBarcos(), bola, ventana));

        ImageView lanchaImg = new ImageView();
        lanchaImg.setImage(new Image(getClass().getResourceAsStream("imagenes/barcoAzul.png")));
        asignarPos(lanchaImg, numbers.remove(0));
        control.addBarco(barcoLanEsp = new Barcos("lancha", "Azul", lanchaImg, control.getBarcos(), bola,ventana));

        ImageView submarinoImg = new ImageView();
        submarinoImg.setImage(new Image(getClass().getResourceAsStream("imagenes/lanchaAzul.png")));
        asignarPos(submarinoImg, numbers.remove(0));
        control.addBarco(barcoSubEsp = new Barcos("submarino", "Azul", submarinoImg, control.getBarcos(), bola, ventana));

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        Collections.shuffle(numbers);

        ImageView destructorImg2 = new ImageView();
        destructorImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorRojo.png")));

        asignarPosFr(destructorImg2, numbers.remove(0));
        control.addBarco(barcoDesFr = new Barcos("destructor", "Rojo", destructorImg2, control.getBarcos(), bola, ventana));

        ImageView acorazadoImg2 = new ImageView();
        acorazadoImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/acorazadoRojo.png")));
        asignarPosFr(acorazadoImg2, numbers.remove(0));
        control.addBarco(barcoAcoFr = new Barcos("acorazado", "Rojo", acorazadoImg2, control.getBarcos(), bola, ventana));

        ImageView lanchaImg2 = new ImageView();
        lanchaImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/barcoRojo.png")));
        asignarPosFr(lanchaImg2, numbers.remove(0));
        control.addBarco(barcoLanFr = new Barcos("lancha", "Rojo", lanchaImg2, control.getBarcos(), bola, ventana));


        ImageView submarinoImg2 = new ImageView();
        submarinoImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/lanchaRoja.png")));
        asignarPosFr(submarinoImg2, numbers.remove(0));
        control.addBarco(barcoSubFr = new Barcos("submarino", "Rojo", submarinoImg2, control.getBarcos(), bola,ventana));

        //Mostrar ventana Equipo Rojo
        PestañaEquipoRojo pestRojo = new PestañaEquipoRojo();
        pestRojo.getControl(control);
        pestRojo.show();

        //Mostrar ventana Equipo Azul
        PestañaEquipoAzul pestAzul = new PestañaEquipoAzul();
        pestAzul.getControl(control);
        pestAzul.show();

        ventana.getChildren().addAll(barcoDesEsp.getImagenBarco(), barcoDesFr.getImagenBarco(), barcoAcoEsp.getImagenBarco(), barcoAcoFr.getImagenBarco(),
                barcoLanEsp.getImagenBarco(), barcoLanFr.getImagenBarco(), barcoSubEsp.getImagenBarco(), barcoSubFr.getImagenBarco(), bola);



        control.ganador();

    }
}