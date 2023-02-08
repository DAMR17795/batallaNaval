package com.example.batallanaval;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class PestañaGlobal extends Stage {
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
    public PestañaGlobal() {
        cargarInterfaz();
    }
}
