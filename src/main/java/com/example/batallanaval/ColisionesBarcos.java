package com.example.batallanaval;

import java.util.Random;

public class ColisionesBarcos {
    public static void detectarColision(Barcos barco) {
        double x = barco.getImagenBarco().getLayoutX();
        double y = barco.getImagenBarco().getLayoutY();

        if (barco.getNombreBarco().equals("destructor")) {
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
            if (randomNumber <= 5) {
                if (x < 0 || x > Constantes.ANCHO_VENTANA_Destructor) {
                    barco.setDireccionBarco(180 + barco.getDireccionBarco());
                }
                if (y < 0 || y > Constantes.ALTO_VENTANA_Destructor) {
                    barco.setDireccionBarco(180 + barco.getDireccionBarco());
                }
            } else {
                if (x < 0 || x > Constantes.ANCHO_VENTANA_Destructor) {
                    barco.setDireccionBarco(-180 + barco.getDireccionBarco());
                }
                if (y < 0 || y > Constantes.ALTO_VENTANA_Destructor) {
                    barco.setDireccionBarco(-barco.getDireccionBarco());
                }
            }
        } else {
            if (barco.getNombreBarco().equals("submarino")) {
                Random random = new Random();
                int randomNumber = random.nextInt(10) + 1;
                if (randomNumber >= 5) {
                    if (x < 0 || x > Constantes.ANCHO_VENTANA_Submarino) {
                        barco.setDireccionBarco(180 + barco.getDireccionBarco());
                    }
                    if (y < 0 || y > Constantes.ALTO_VENTANA_Submarino) {
                        barco.setDireccionBarco(180 + barco.getDireccionBarco());
                    }
                } else {
                    if (x < 0 || x > Constantes.ANCHO_VENTANA_Submarino) {
                        barco.setDireccionBarco(180 - barco.getDireccionBarco());
                    }
                    if (y < 0 || y > Constantes.ALTO_VENTANA_Submarino) {
                        barco.setDireccionBarco(-barco.getDireccionBarco());
                    }
                }
            } else {
                if (barco.getNombreBarco().equals("lancha")) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;
                    if (randomNumber <= 5) {
                        if (x < 0 || x > Constantes.ANCHO_VENTANA_lancha) {
                            barco.setDireccionBarco(180 + barco.getDireccionBarco());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_lancha) {
                            barco.setDireccionBarco(180 + barco.getDireccionBarco());
                        }
                    } else {
                        if (x < 0 || x > Constantes.ANCHO_VENTANA_lancha) {
                            barco.setDireccionBarco(180 - barco.getDireccionBarco());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_lancha) {
                            barco.setDireccionBarco(-barco.getDireccionBarco());
                        }
                    }
                } else {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;
                    if (randomNumber <= 5) {
                        if (x < 0 || x > Constantes.ANCHO_VENTANA_acorazado) {
                            barco.setDireccionBarco(180 + barco.getDireccionBarco());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_acorazado) {
                            barco.setDireccionBarco(180 + barco.getDireccionBarco());
                        }
                    } else {
                        if (x < 0 || x > Constantes.ANCHO_VENTANA_acorazado) {
                            barco.setDireccionBarco(180 - barco.getDireccionBarco());
                        }
                        if (y < 0 || y > Constantes.ALTO_VENTANA_acorazado) {
                            barco.setDireccionBarco(-barco.getDireccionBarco());
                        }
                    }

                }

            }

        }

    }

}
