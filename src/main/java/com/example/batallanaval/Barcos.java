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

    private MediaPlayer mediaPlayer;
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
    private AnchorPane fondo;

    public Barcos(String nombre, String equipo, ImageView imagenBarco, ArrayList<Barcos> barcos, ImageView bola, AnchorPane ventana) {
        this.nombre = nombre;
        this.imagenBarco = imagenBarco;
        this.barcos = barcos;
        this.fondo = ventana;
        this.modoDisparo = false;
        this.direccion = 45;

        this.bolaCañon = bola;

        this.equipo = equipo;
        this.recagarDisparo = 0;

        if (nombre.contains("lancha")) {
            imagenBarco.setFitHeight(30);
            imagenBarco.setFitWidth(30);
            vida = 10;
            //10
            velocidad = 15;
            sonar = 75;
            potenciaFuego = 20;
            tiempoRecarga = 2000;
        } else if (nombre.contains("acorazado")) {
            imagenBarco.setFitHeight(90);
            imagenBarco.setFitWidth(90);
            vida = 120;
            //3
            velocidad = 8;
            sonar = 204;
            potenciaFuego = 80;
            tiempoRecarga = 8000;

        } else if (nombre.contains("submarino")) {
            imagenBarco.setFitHeight(40);
            imagenBarco.setFitWidth(40);
            vida = 30;
            //2
            velocidad = 7;
            sonar = 102;
            potenciaFuego = 60;
            tiempoRecarga = 4000;
        } else if (nombre.contains("destructor")) {

            imagenBarco.setFitHeight(70);
            imagenBarco.setFitWidth(70);
            vida = 80;
            //5
            velocidad = 10;
            sonar = 153;
            potenciaFuego = 50;
            tiempoRecarga = 6000;
        }

        moverse = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {


            acabarJuego();
            if (!modoDisparo) {
                detectarBarcosCercanos();
                detectarParedes();
                moverBarco();
            }
            barcoMuerto();

        }));
        moverse.setCycleCount(Timeline.INDEFINITE);
        moverse.play();

    }

    public synchronized boolean isModoDisparo() {
        return modoDisparo;
    }

    public synchronized void setModoDisparo(boolean modoDisparo) {
        this.modoDisparo = modoDisparo;
    }

    public synchronized void pararBarcos(Barcos barco1, Barcos barco2) {
        barco1.setModoDisparo(true);
        barco2.setModoDisparo(true);
    }

    public synchronized void moverBarcos() {
        for (Barcos barco : barcos) {
            barco.setModoDisparo(false);
        }
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public synchronized void acabarJuego() {

        int contador = 0;
        int barcoEsp = 0;
        int barcoFr = 0;

        for (Barcos barco : barcos) {

            if (barco.getVida() > 0) {

                if (barco.getEquipo().equals("España")) {
                    barcoEsp++;
                }

                if (barco.getEquipo().equals("Francia")) {
                    barcoFr++;
                }
                contador++;
            }
        }


        if (barcoEsp >= 1 && barcoFr == 0) {
            moverse.stop();
        }

        if (barcoFr >= 1 && barcoEsp == 0) {
            moverse.stop();
        }
    }


    private long tiempoUltimoDisparo = 0;

    public synchronized boolean recargando() {
        long tiempoActual = System.currentTimeMillis();
        return tiempoActual < tiempoUltimoDisparo + tiempoRecarga;
    }


    public synchronized Timeline getMoverse() {
        return moverse;
    }

    public synchronized void setMoverse(Timeline moverse) {
        this.moverse = moverse;
    }

    public void sonidoDisparo() {
        Platform.runLater(() -> {
            Media pick = new Media(this.getClass().getResource("musica/aa.mp3").toString());
            mediaPlayer= new MediaPlayer(pick);
            mediaPlayer.play();
        });
    }


    public void animacionDisparo(Barcos barco1, Barcos barco2) {

        ImageView bola = new ImageView((new Image((getClass().getResourceAsStream("imagenes/cannonball.png")))));
        bola.setFitWidth(15);
        bola.setFitHeight(15);
        fondo.getChildren().add(bola);


        double barco1X = barco1.getImagenBarco().getBoundsInParent().getMinX() + barco1.getImagenBarco().getBoundsInParent().getWidth() / 2;
        double barco1Y = barco1.getImagenBarco().getBoundsInParent().getMinY() + barco1.getImagenBarco().getBoundsInParent().getHeight() / 2;

        if (barco1.getNombre().equals("lancha") || barco1.getNombre().equals("destructor")) {
            barco1X -= 6;
            barco1Y -= 6;
        }

        double barco2X = barco2.getImagenBarco().getBoundsInParent().getMinX() + barco2.getImagenBarco().getBoundsInParent().getWidth() / 2;
        double barco2Y = barco2.getImagenBarco().getBoundsInParent().getMinY() + barco2.getImagenBarco().getBoundsInParent().getHeight() / 2;


        if (barco2.getNombre().equals("lancha") || barco2.getNombre().equals("destructor")) {
            barco2X -= 6;
            barco2Y -= 6;
        }

        Timeline animacion = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(bola.xProperty(), barco1X),
                        new KeyValue(bola.yProperty(), barco1Y)),
                new KeyFrame(Duration.seconds(2), new KeyValue(bola.xProperty(), barco2X),
                        new KeyValue(bola.yProperty(), barco2Y))
        );

        animacion.setOnFinished(e -> {
            int ultimoIndex = fondo.getChildren().size() - 1;
            fondo.getChildren().remove(ultimoIndex);
            barco1.setModoDisparo(false);
            barco2.setModoDisparo(false);
            cambiarImagenBarco(barco2);
        });

        animacion.play();


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
                distancia -= 50;
            }

            if (distancia < getSonar() && this.getEquipo() != barco.getEquipo() && barco.getVida() > 0) {
                pararBarcos(this, barco);
                tiempoUltimoDisparo = System.currentTimeMillis();
                int disparar = this.disparar();
                System.out.println("El barco: " + this.getNombre() + " " + this.getEquipo() + " dispara a: " + barco.getNombre() + " " + barco.getEquipo());
                System.out.println("Le quita: " + disparar);
                barco.setVida(barco.getVida() - disparar);
                System.out.println("Le queda de vida: " + barco.getVida());
                sonidoDisparo();
                animacionDisparo(this, barco);
                break;
            }
        }
    }

    public synchronized void cambiarImagenBarco(Barcos barco) {
        if (barco.getVida() <= 0) {

            barco.moverse.stop();

            ImageView muerto = new ImageView(new Image(getClass().getResourceAsStream("imagenes/explosion.png")));
            barco.imagenBarco.setImage(muerto.getImage());
            barco.imagenBarco.setRotate(0);

            barco.imagenBarco.setFitHeight(20);
            barco.imagenBarco.setFitWidth(20);

            Platform.runLater(() -> {


                Media pick = new Media(this.getClass().getResource("musica/explosion.mp3").toString());
                mediaPlayer= new MediaPlayer(pick);
                mediaPlayer.play();

            });

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    barco.fondo.getChildren().remove(barco.getImagenBarco());
                }
            }));
            timeline.play();
            barco.vida = 0;
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
