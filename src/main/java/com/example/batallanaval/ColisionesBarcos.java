package com.example.batallanaval;

import java.util.Random;

public class ColisionesBarcos {
    public static void detectarColision(Barcos barco) {
        double x = barco.getImagenBarco().getLayoutX();
        double y = barco.getImagenBarco().getLayoutY();

        if (barco.getNombre().equals("destructor")) {

            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;

            if (randomNumber <= 5) {

                if (x < 0 || x > Constantes.ANCHO_VENTANA_Destructor) {
                    barco.setDireccion(180 + barco.getDireccion());
                }
                if (y < 0 || y > Constantes.ALTO_VENTANA_Destructor) {
                    barco.setDireccion(180 + barco.getDireccion());
                }

            } else {

                if (x < 0 || x > Constantes.ANCHO_VENTANA_Destructor) {
                    barco.setDireccion(-180 + barco.getDireccion());
                }
                if (y < 0 || y > Constantes.ALTO_VENTANA_Destructor) {
                    barco.setDireccion(-barco.getDireccion());
                }
            }


        } else {

            if (barco.getNombre().equals("submarino")) {
                Random random = new Random();
                int randomNumber = random.nextInt(10) + 1;

                if (randomNumber >= 5) {
                    if (x < 0 || x > Constantes.ANCHO_VENTANA_Submarino) {
                        barco.setDireccion(180 + barco.getDireccion());
                    }
                    if (y < 0 || y > Constantes.ALTO_VENTANA_Submarino) {
                        barco.setDireccion(180 + barco.getDireccion());
                    }
                } else {

                    if (x < 0 || x > Constantes.ANCHO_VENTANA_Submarino) {
                        barco.setDireccion(180 - barco.getDireccion());
                    }
                    if (y < 0 || y > Constantes.ALTO_VENTANA_Submarino) {
                        barco.setDireccion(-barco.getDireccion());
                    }

                }

            } else {
                if (barco.getNombre().equals("lancha")) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;


                    if (randomNumber <= 5) {

                        if (x < 0 || x > Constantes.ANCHO_VENTANA_lancha) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_lancha) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }

                    } else {

                        if (x < 0 || x > Constantes.ANCHO_VENTANA_lancha) {
                            barco.setDireccion(180 - barco.getDireccion());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_lancha) {
                            barco.setDireccion(-barco.getDireccion());
                        }

                    }


                } else {

                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;

                    if (randomNumber <= 5) {

                        if (x < 0 || x > Constantes.ANCHO_VENTANA_acorazado) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_acorazado) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }

                    } else {

                        if (x < 0 || x > Constantes.ANCHO_VENTANA_acorazado) {
                            barco.setDireccion(180 - barco.getDireccion());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_acorazado) {
                            barco.setDireccion(-barco.getDireccion());
                        }

                    }


                }


            }


        }


    }


    public static boolean sonarDisparo(Barcos barco1, Barcos barco2) {
        double distancia = distanciaEntreBarcos(barco1, barco2);
        return distancia <= barco1.getSonar();
    }

    private static double distanciaEntreBarcos(Barcos barco1, Barcos barco2) {
        double x1 = barco1.getImagenBarco().getLayoutX();
        double y1 = barco1.getImagenBarco().getLayoutY();
        double x2 = barco2.getImagenBarco().getLayoutX();
        double y2 = barco2.getImagenBarco().getLayoutY();
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
