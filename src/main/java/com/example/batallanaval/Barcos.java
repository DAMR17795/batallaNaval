package com.example.batallanaval;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Barcos {

    private String nombre;
    private int vida;
    private int velocidad;
    private int sonar;
    private int potenciaFuego;
    private ImageView imagenBarco;
    private double x;

    private double direccion;

    private Timeline moverse;

    private ArrayList<Barcos> barcos;

    private int recagarDisparo;

    private String equipo;

    private ImageView bolaCañon;

    private String winner;

    private boolean modoDisparo;

    private long tiempoRecarga;

    public Barcos(String nombre, String equipo, ImageView imagenBarco, ArrayList<Barcos> barcos, ImageView bala) {
        //Atributos
        this.nombre = nombre;
        this.imagenBarco = imagenBarco;
        this.barcos = barcos;

        this.modoDisparo = false;
        this.direccion = 45;

        this.bolaCañon = bala;


        this.equipo = equipo;
        this.recagarDisparo = 0;

        if (nombre.contains("lancha")) {
            imagenBarco.setFitHeight(30);
            imagenBarco.setFitWidth(30);
            vida = 10;
            velocidad = 10;
            sonar = 2000;
            potenciaFuego = 20;
            tiempoRecarga = 2000;
        } else if (nombre.contains("acorazado")) {
            imagenBarco.setFitHeight(90);
            imagenBarco.setFitWidth(90);
            vida = 120;
            velocidad = 3;
            sonar = 2000;
            potenciaFuego = 80;
            tiempoRecarga = 8000;

        } else if (nombre.contains("submarino")) {
            imagenBarco.setFitHeight(40);
            imagenBarco.setFitWidth(40);
            vida = 30;
            velocidad = 2;
            sonar = 2000;
            potenciaFuego = 60;
            tiempoRecarga = 4000;
        } else if (nombre.contains("destructor")) {

            imagenBarco.setFitHeight(70);
            imagenBarco.setFitWidth(70);
            vida = 80;
            velocidad = 5;
            sonar = 2000;
            potenciaFuego = 50;
            tiempoRecarga = 6000;
        }

        moverse = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {

            barcoMuerto();
            acabarJuego();
            detectarBarcosCercanos();
            detectarParedes();
            moverBarco();


        }));
        moverse.setCycleCount(Timeline.INDEFINITE);
        moverse.play();

    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public synchronized void acabarJuego() {

        int contador = 0;
        int barcosAzules = 0;
        int barcosRojos = 0;

        for (Barcos barco : barcos) {

            if (barco.getVida() > 0) {


                if (barco.getEquipo().equals("Azul")) {

                    barcosAzules++;
                }

                if (barco.getEquipo().equals("Rojo")) {

                    barcosRojos++;

                }
                contador++;

            }

        }


        if (barcosAzules >= 1 && barcosRojos == 0) {

            moverse.stop();


        }

        if (barcosRojos >= 1 && barcosAzules == 0) {

            moverse.stop();

        }
    }


    private long tiempoUltimoDisparo = 0;

    private boolean recargando() {
        long tiempoActual = System.currentTimeMillis();
        return tiempoActual < tiempoUltimoDisparo + tiempoRecarga;
    }


    public synchronized Timeline getMoverse() {
        return moverse;
    }

    public synchronized void setMoverse(Timeline moverse) {
        this.moverse = moverse;
    }

    public synchronized void detectarBarcosCercanos() {

        if (recargando() || getVida() <= 0) {
            return;
        }

        for (Barcos barco : barcos) {
            if (barco == this) {
                continue;
            }
            double distancia = Math.sqrt(Math.pow(barco.getImagenBarco().getLayoutX() - this.getImagenBarco().getLayoutX(), 2) +
                    Math.pow(barco.getImagenBarco().getLayoutY() - this.getImagenBarco().getLayoutY(), 2));

            if (barco.getNombre().contains("submarino")) {
                distancia -= 10;
            }

            if (distancia < getSonar() && this.getEquipo() != barco.getEquipo() && barco.getVida() > 0) {
                tiempoUltimoDisparo = System.currentTimeMillis();
                int disparar = this.disparar();
                System.out.println("El barco: " + this.getNombre() + " " + this.getEquipo() + " dispara a: " + barco.getNombre() + " " + barco.getEquipo());
                System.out.println("Le quita: " + disparar);
                barco.setVida(barco.getVida() - disparar);
                System.out.println("Le queda de vida: " + barco.getVida());
                break;
            }
        }
    }


    public synchronized void barcoMuerto() {

        if (this.getVida() <= 0) {

            moverse.stop();

            this.vida = 0;
        }

    }


    public synchronized void moverBarco() {

        double x = this.getImagenBarco().getLayoutX();
        double y = this.getImagenBarco().getLayoutY();
        double velocidad = this.getVelocidad();
        double direccion = Math.toRadians(this.getDireccion());
        x += velocidad * Math.cos(direccion);
        y += velocidad * Math.sin(direccion);
        this.getImagenBarco().setLayoutX(x);
        this.getImagenBarco().setLayoutY(y);
        this.getImagenBarco().setRotate(this.getDireccion());

    }

    public synchronized void detectarParedes() {
        ColisionesBarcos.detectarColision(this);
    }


    public synchronized int disparar() {
        Random rand = new Random();
        int random = rand.nextInt(101);
        if (random < 25) {
            return 0;
        } else if (random < 50) {
            return potenciaFuego / 2;
        } else {
            return potenciaFuego;
        }
    }

    public double getDireccion() {
        return direccion;
    }

    public void setDireccion(double direccion) {
        this.direccion = direccion;
    }

    public ImageView getImagenBarco() {
        return imagenBarco;
    }

    public double barcoX() {

        return imagenBarco.getLayoutX();

    }

    public double barcoY() {

        return imagenBarco.getLayoutY();
    }

    public void moverBarco(double posX, double posY) {
        imagenBarco.setLayoutX(posX);
        imagenBarco.setLayoutY(posY);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getSonar() {
        return sonar;
    }

    public void setSonar(int sonar) {
        this.sonar = sonar;
    }

    public int getPotenciaFuego() {
        return potenciaFuego;
    }

    public void setPotenciaFuego(int potenciaFuego) {
        this.potenciaFuego = potenciaFuego;
    }


}
