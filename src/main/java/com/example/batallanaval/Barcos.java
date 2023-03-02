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
    private Timeline movimientoBarco;
    private long tiempoRecarga;
    private ArrayList<Barcos> barcos;
    private int sonarBarco;
    private int potenciaDisparoBarco;
    private String nombreBarco;
    private int vidaBarco;
    private int velocidadBarco;
    private ImageView imagenBarco;
    private double direccionBarco;
    private String nombreEquipo;
    private boolean modoDisparo;
    private AnchorPane fondo;
    private long tiempoUltimoDisparo = 0;

    /**
     * Constructor de objetos de Barcos, en este constructor
     * se introducen parámetros para construir el objeto perteneciente
     * a la clase Barcos
     * @param nombreBarco -> nombre que tendrá el barco (submarino, acorazado, destructor o lancha)
     * @param nombreEquipo -> nombre del equipo al que pertenecerá (Rojo o Azul)
     * @param imagenBarco -> imagen que tendrá el objeto (submarino, acorazado, destructor o lancha)
     * @param barcos -> ArrayList en el que se guardarán los barcos
     * @param ventana -> ventana en la que se situará el barco
     */
    public Barcos(String nombreBarco, String nombreEquipo, ImageView imagenBarco, ArrayList<Barcos> barcos, AnchorPane ventana) {
        //Parámetros de barco
        this.nombreBarco = nombreBarco;
        this.nombreEquipo = nombreEquipo;
        this.imagenBarco = imagenBarco;
        this.barcos = barcos;
        this.fondo = ventana;
        this.modoDisparo = false;
        this.direccionBarco = 45;

        //Según el tipo de barco le damos sus características
        if (nombreBarco.contains("acorazado")) {
            vidaBarco = 120;
            //3
            velocidadBarco = 8;
            sonarBarco = 204;
            potenciaDisparoBarco = 80;
            tiempoRecarga = 8000;
            imagenBarco.setFitHeight(90);
            imagenBarco.setFitWidth(90);
        } else if (nombreBarco.contains("lancha")) {
            vidaBarco = 10;
            //10
            velocidadBarco = 15;
            sonarBarco = 75;
            potenciaDisparoBarco = 20;
            tiempoRecarga = 2000;
            imagenBarco.setFitHeight(30);
            imagenBarco.setFitWidth(30);
        } else if (nombreBarco.contains("destructor")) {
            vidaBarco = 80;
            //5
            velocidadBarco = 10;
            sonarBarco = 153;
            potenciaDisparoBarco = 50;
            tiempoRecarga = 6000;
            imagenBarco.setFitHeight(70);
            imagenBarco.setFitWidth(70);
        } else if (nombreBarco.contains("submarino")) {
            vidaBarco = 30;
            //2
            velocidadBarco = 7;
            sonarBarco = 102;
            potenciaDisparoBarco = 60;
            tiempoRecarga = 4000;
            imagenBarco.setFitHeight(40);
            imagenBarco.setFitWidth(40);
        }

        //Movimiento de los barcos
        movimientoBarco = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {

            //Si modo disparo está a false
            //mueve barco detectando barcos
            //y colisiones
            if (!modoDisparo) {
                deteccionBarcos();
                detectarParedes();
                moverBarco();
            }
            //Para barco si está muerto
            pararBarcoSiMuerto();
            //Cambia imagen por humo
            barcoTocado();

        }));
        movimientoBarco.setCycleCount(Timeline.INDEFINITE);
        movimientoBarco.play();

    }

    /**
     * Método utilizado para poder mover los barcos,
     * obtiene primeramente la posición del barco
     * dentro del panel y la velocidad que tiene en ese momento,
     * para así sumar a la posición actual, la velocidad *
     * la dirección y así poder mover el barco
     */
    public synchronized void moverBarco() {
        double x = this.getImagenBarco().getLayoutX();
        double y = this.getImagenBarco().getLayoutY();
        double velocidad = this.getVelocidadBarco();
        double direccion = Math.toRadians(this.getDireccionBarco());
        x += velocidad * Math.cos(direccion);
        y += velocidad * Math.sin(direccion);
        this.getImagenBarco().setLayoutX(x);
        this.getImagenBarco().setLayoutY(y);
        this.getImagenBarco().setRotate(this.getDireccionBarco());
    }

    /**
     * Método para disparar, consiste en coger un
     * numero random entre 0 y 100, si el numero
     * es menor de 25, quita 0 de vida al barco
     * que disparó si está entre 25 y 50
     * le quita la mitad de la potencia de disparo
     * y si es mayor a 50 le quita de vida toda la
     * potencia de disparo que tenga el barco
     * @return
     */
    public synchronized int disparar() {
        Random rand = new Random();
        int random = rand.nextInt(101);
        if (random < 25) {
            return 0;
        } else if (random < 50) {
            return potenciaDisparoBarco / 2;
        } else {
            return potenciaDisparoBarco;
        }
    }

    /**
     * Método para detectar los barcos,
     * obtiene la distancia entre los barcos y si dicha distancia
     * se cumple, los dos barcos se paran y el que primero
     * encontró al otro dispara. Mientras están recargando
     * no disparan.
     */
    public synchronized void deteccionBarcos() {
        if (recargando() || getVidaBarco() <= 0) {
            return;
        }

        for (Barcos barco : barcos) {
            if (barco == this) {
                continue;
            }
            double distancia = Math.sqrt(Math.pow(barco.getImagenBarco().getLayoutX() - this.getImagenBarco().getLayoutX(), 2) +
                    Math.pow(barco.getImagenBarco().getLayoutY() - this.getImagenBarco().getLayoutY(), 2));

            if (barco.getNombreBarco().contains("submarino")) {
                distancia -= 50;
            }

            if (distancia < getSonarBarco() && this.getNombreEquipo() != barco.getNombreEquipo() && barco.getVidaBarco() > 0) {
                pararBarcos(this, barco);
                tiempoUltimoDisparo = System.currentTimeMillis();
                int disparar = this.disparar();
                barco.setVidaBarco(barco.getVidaBarco() - disparar);
                cargarSonidoCañon();
                balaCañonMovimiento(this, barco);
                break;
            }
        }
    }

    /**
     * Método para parar barcos, cuando se enecuentran
     * @param barco1 -> barco que se encuentra
     * @param barco2 -> barco que se encuentra
     */
    public synchronized void pararBarcos(Barcos barco1, Barcos barco2) {
        barco1.setModoDisparo(true);
        barco2.setModoDisparo(true);
    }

    /**
     * Método en el que se establece el tiempo
     * que está el barco recargando
     * @return
     */
    public synchronized boolean recargando() {
        long tiempoActual = System.currentTimeMillis();
        return tiempoActual < tiempoUltimoDisparo + tiempoRecarga;
    }

    /**
     * Método para cargar el sonido de
     * disparo mediante un hilo
     */
    public void cargarSonidoCañon() {
        Platform.runLater(() -> {
            Media pick = new Media(this.getClass().getResource("musica/disparo.mp3").toString());
            mediaPlayer= new MediaPlayer(pick);
            mediaPlayer.play();
        });
    }

    /**
     * Método para establecer el movimiento de la bala del cañon
     * del barco que disparó al otro. Recibe dos barcos
     * por parámetro, carga la imagen de la bala del cañón
     * y dependiendo del barco que se trate irá la bala a una posición
     * definida del barco u otra, ya que cada barco al tener distintos
     * tamaños para que la bala vaya centrada hay que corregir su posición.
     * Por último se carga la animación de la bala.
     * @param barco1 -> barco que entra por parámetro
     * @param barco2 -> barco que entra por parámetro
     */
    public void balaCañonMovimiento(Barcos barco1, Barcos barco2) {

        ImageView bala = new ImageView((new Image((getClass().getResourceAsStream("imagenes/cannonball.png")))));
        fondo.getChildren().add(bala);
        bala.setFitWidth(25);
        bala.setFitHeight(25);

        double barco1X = barco1.getImagenBarco().getBoundsInParent().getMinX() + barco1.getImagenBarco().getBoundsInParent().getWidth() / 2;
        double barco1Y = barco1.getImagenBarco().getBoundsInParent().getMinY() + barco1.getImagenBarco().getBoundsInParent().getHeight() / 2;

        if (barco1.getNombreBarco().equals("lancha") || barco1.getNombreBarco().equals("destructor")) {
            barco1X -= 6;
            barco1Y -= 6;
        }

        double barco2X = barco2.getImagenBarco().getBoundsInParent().getMinX() + barco2.getImagenBarco().getBoundsInParent().getWidth() / 2;
        double barco2Y = barco2.getImagenBarco().getBoundsInParent().getMinY() + barco2.getImagenBarco().getBoundsInParent().getHeight() / 2;


        if (barco2.getNombreBarco().equals("lancha") || barco2.getNombreBarco().equals("destructor")) {
            barco2X -= 6;
            barco2Y -= 6;
        }

        Timeline animacion = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(bala.xProperty(), barco1X),
                        new KeyValue(bala.yProperty(), barco1Y)),
                new KeyFrame(Duration.seconds(0.15), new KeyValue(bala.xProperty(), barco2X),
                        new KeyValue(bala.yProperty(), barco2Y))
        );

        animacion.setOnFinished(e -> {
            int ultimoIndex = fondo.getChildren().size() - 1;
            fondo.getChildren().remove(ultimoIndex);
            barco1.setModoDisparo(false);
            barco2.setModoDisparo(false);
            pasarABarcoMuerto(barco2);
        });

        animacion.play();

    }


    /**
     * Método para cambiar la imagen del barco
     * a otra que contenga humo cuando al barco
     * se le quita un 80% de la vida
     */
    public synchronized void barcoTocado() {
        if (this.getNombreBarco().equals("acorazado") && this.getVidaBarco() <= 80) {
            if (this.getNombreEquipo().equals("Azul")) {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/acorazadoAzulHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            } else {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/acorazadoRojoHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            }
        }
        if (this.getNombreBarco().equals("submarino") && this.getVidaBarco() <= 25) {
            if (this.getNombreEquipo().equals("Azul")) {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/lanchaAzulHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            } else {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/lanchaRojaHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            }
        }
        if (this.getNombreBarco().equals("lancha") && this.getVidaBarco() <= 7) {
            if (this.getNombreEquipo().equals("Azul")) {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/barcoAzulHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            } else {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/barcoRojoHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            }
        }
        if (this.getNombreBarco().equals("destructor") && this.getVidaBarco() <= 60) {
            if (this.getNombreEquipo().equals("Azul")) {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/destructorAzulHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            } else {
                ImageView tocado = new ImageView(new Image(getClass().getResourceAsStream("imagenes/destructorRojoHumo.png")));
                this.imagenBarco.setImage(tocado.getImage());
            }
        }

    }

    /**
     * Método para cambiar la imagen del barco a
     * una explosión cuando su vida llega a 0.
     * Después de ello deja de moverse el barco y desaparece la imagen
     * @param barco -> barco que entra dentro del método
     */
    public synchronized void pasarABarcoMuerto(Barcos barco) {
        if (barco.getVidaBarco() <= 0) {
            //Preparamos imagen
            ImageView muerto = new ImageView(new Image(getClass().getResourceAsStream("imagenes/explosion.png")));
            barco.imagenBarco.setImage(muerto.getImage());
            barco.imagenBarco.setRotate(0);

            //Según su nombre ajustamos la imagen
            //más o menos grande
            if (barco.getNombreBarco().equals("acorazado")) {
                barco.imagenBarco.setFitHeight(90);
                barco.imagenBarco.setFitWidth(120);
            }

            if (barco.getNombreBarco().equals("lancha")) {
                barco.imagenBarco.setFitHeight(60);
                barco.imagenBarco.setFitWidth(60);
            }

            if (barco.getNombreBarco().equals("submarino")) {
                barco.imagenBarco.setFitHeight(70);
                barco.imagenBarco.setFitWidth(70);
            }

            if (barco.getNombreBarco().equals("destructor")) {
                barco.imagenBarco.setFitHeight(70);
                barco.imagenBarco.setFitWidth(100);
            }

            //Deja de moverse
            barco.movimientoBarco.stop();

            //Hilo para el sonido de muerte
            Platform.runLater(() -> {
                Media pick = new Media(this.getClass().getResource("musica/explosion.mp3").toString());
                mediaPlayer= new MediaPlayer(pick);
                mediaPlayer.play();
            });

            //Duración de la imagen antes de ser removida
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    barco.fondo.getChildren().remove(barco.getImagenBarco());
                }
            }));
            timeline.play();
            //Vida del barco a 0
            barco.vidaBarco = 0;
        }
    }

    /**
     * Método para parar el barco si ha muerto,
     * es decir su vida es menor o igual a 0
     */
    public synchronized void pararBarcoSiMuerto() {
        if (this.getVidaBarco() <= 0) {
            movimientoBarco.stop();
            this.vidaBarco = 0;
        }
    }

    public synchronized void setModoDisparo(boolean modoDisparo) {
        this.modoDisparo = modoDisparo;
    }

    public double getDireccionBarco() {
        return direccionBarco;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setDireccionBarco(double direccionBarco) {
        this.direccionBarco = direccionBarco;
    }

    public ImageView getImagenBarco() {
        return imagenBarco;
    }

    public String getNombreBarco() {
        return nombreBarco;
    }

    public int getVidaBarco() {
        return vidaBarco;
    }

    public void setVidaBarco(int vidaBarco) {
        this.vidaBarco = vidaBarco;
    }

    public int getVelocidadBarco() {
        return velocidadBarco;
    }

    public int getSonarBarco() {
        return sonarBarco;
    }
    public synchronized void detectarParedes() {
        ColisionesBarcos.detectarColision(this);
    }


}
