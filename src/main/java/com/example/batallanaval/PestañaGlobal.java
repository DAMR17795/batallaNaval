package com.example.batallanaval;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PestañaGlobal extends Stage {
    /**
     * Método que carga la interfaz de la pestaña Global
     */
    private void cargarInterfaz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("marcadorGlobal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 589, 219);
            setScene(scene);
            setX(0);
            setY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que carga la pestaña Global
     */
    public PestañaGlobal() {
        cargarInterfaz();
    }
}
