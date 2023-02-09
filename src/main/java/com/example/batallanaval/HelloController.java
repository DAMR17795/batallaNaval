package com.example.batallanaval;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloController {
    Barcos lanchaRoja;
    Barcos acorazadoRojo;
    Barcos destructorRojo;
    Barcos submarinoRojo;
    Barcos lanchaAzul;
    Barcos acorazadoAzul;
    Barcos destructorAzul;
    Barcos submarinoAzul;
    ControlJuego control;
    @FXML
    private AnchorPane ventana;
    @FXML
    private AnchorPane anchorAzul;
    @FXML
    private AnchorPane anchorRojo;
    @FXML
    private AnchorPane globalMark;

    List<Integer> posicionesBarcos = new ArrayList<>();

    public void initialize()  {
        Image fondo = new Image(getClass().getResourceAsStream("imagenes/fondo.png"));
        ImageView back = new ImageView(fondo);
        ventana.setBackground(new Background(new BackgroundImage(back.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        creacionBarcos();
    }


    public void posicionAzules(ImageView imagen, int num) {

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

    public void posicionRojos(ImageView imagen, int num) {

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
        posicionesBarcos.add(1);
        posicionesBarcos.add(2);
        posicionesBarcos.add(3);
        posicionesBarcos.add(4);
        Collections.shuffle(posicionesBarcos);
        control = new ControlJuego();

        //Cargamos Bala
        ImageView balaCañon = new ImageView(new Image((getClass().getResourceAsStream("imagenes/cannonball.png"))));
        balaCañon.setFitWidth(30);
        balaCañon.setFitHeight(30);

        //Cargamos Destructor Azul
        ImageView destructorImg = new ImageView();
        destructorImg.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorAzul.png")));
        ImageView destructorDisImg = new ImageView();
        destructorDisImg.setImage(new Image(((getClass().getResourceAsStream("imagenes/recargando.png")))));
        posicionAzules(destructorImg, posicionesBarcos.remove(0));
        control.addBarco(destructorAzul = new Barcos("destructor", "Azul", destructorImg, control.getBarcos(),ventana));

        //Cargamos Acorazado Azul
        ImageView acorazadoImg = new ImageView();
        acorazadoImg.setImage(new Image(getClass().getResourceAsStream("imagenes/acorazadoAzul.png")));
        ImageView acorazadoDisImg = new ImageView();
        acorazadoDisImg.setImage(new Image(((getClass().getResourceAsStream("imagenes/recargando.png")))));
        posicionAzules(acorazadoImg, posicionesBarcos.remove(0));
        control.addBarco(acorazadoAzul = new Barcos("acorazado", "Azul", acorazadoImg, control.getBarcos(), ventana));

        //Cargamos Lancha Azul
        ImageView lanchaImg = new ImageView();
        lanchaImg.setImage(new Image(getClass().getResourceAsStream("imagenes/barcoAzul.png")));
        ImageView lanchaDisImg = new ImageView();
        lanchaDisImg.setImage(new Image(((getClass().getResourceAsStream("imagenes/recargando.png")))));
        posicionAzules(lanchaImg, posicionesBarcos.remove(0));
        control.addBarco(lanchaAzul = new Barcos("lancha", "Azul", lanchaImg, control.getBarcos(),ventana));

        //Cargamos Submarino Azul
        ImageView submarinoImg = new ImageView();
        submarinoImg.setImage(new Image(getClass().getResourceAsStream("imagenes/lanchaAzul.png")));
        ImageView submarinoDisImg = new ImageView();
        submarinoDisImg.setImage(new Image(((getClass().getResourceAsStream("imagenes/recargando.png")))));
        posicionAzules(submarinoImg, posicionesBarcos.remove(0));
        control.addBarco(submarinoAzul = new Barcos("submarino", "Azul", submarinoImg, control.getBarcos(), ventana));

        posicionesBarcos.add(1);
        posicionesBarcos.add(2);
        posicionesBarcos.add(3);
        posicionesBarcos.add(4);
        Collections.shuffle(posicionesBarcos);

        //Cargamos Destructor Rojo
        ImageView destructorImg2 = new ImageView();
        destructorImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorRojo.png")));
        posicionRojos(destructorImg2, posicionesBarcos.remove(0));
        control.addBarco(destructorRojo = new Barcos("destructor", "Rojo", destructorImg2, control.getBarcos(), ventana));

        //Cargamos Acorazado Rojo
        ImageView acorazadoImg2 = new ImageView();
        acorazadoImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/acorazadoRojo.png")));
        posicionRojos(acorazadoImg2, posicionesBarcos.remove(0));
        control.addBarco(acorazadoRojo = new Barcos("acorazado", "Rojo", acorazadoImg2, control.getBarcos(), ventana));

        //Cargamos Lancha Roja
        ImageView lanchaImg2 = new ImageView();
        lanchaImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/barcoRojo.png")));
        posicionRojos(lanchaImg2, posicionesBarcos.remove(0));
        control.addBarco(lanchaRoja = new Barcos("lancha", "Rojo", lanchaImg2, control.getBarcos(),ventana));

        //Cargamos Submarino Rojo
        ImageView submarinoImg2 = new ImageView();
        submarinoImg2.setImage(new Image(getClass().getResourceAsStream("imagenes/lanchaRoja.png")));
        posicionRojos(submarinoImg2, posicionesBarcos.remove(0));
        control.addBarco(submarinoRojo = new Barcos("submarino", "Rojo", submarinoImg2, control.getBarcos(), ventana));

        //Mostrar ventana Equipo Rojo
        PestañaEquipoRojo pestRojo = new PestañaEquipoRojo();
        pestRojo.getControl(control);

        //Mostrar ventana Equipo Azul
        PestañaEquipoAzul pestAzul = new PestañaEquipoAzul();
        pestAzul.getControl(control);

        //Marcador Global
        PestañaGlobal pestGlobal = new PestañaGlobal();

        //Ponemos Marcadores
        anchorRojo.getChildren().add(pestRojo.getScene().getRoot());
        anchorAzul.getChildren().add(pestAzul.getScene().getRoot());
        globalMark.getChildren().add(pestGlobal.getScene().getRoot());

        //Ponemos la imagen de los barcos
        ventana.getChildren().addAll(destructorAzul.getImagenBarco(), destructorRojo.getImagenBarco(), acorazadoAzul.getImagenBarco(), acorazadoRojo.getImagenBarco(),
                lanchaAzul.getImagenBarco(), lanchaRoja.getImagenBarco(), submarinoAzul.getImagenBarco(), submarinoRojo.getImagenBarco());

        control.gameOver();

    }
}