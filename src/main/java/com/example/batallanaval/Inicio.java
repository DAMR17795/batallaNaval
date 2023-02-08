package com.example.batallanaval;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Inicio extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1535, 768);
        primaryStage.setTitle("Batalla Naval");

        primaryStage.getIcons().add(new Image(this.getClass().getResource("images/iconoApp.png").toString()));
        primaryStage.setX(0);
        primaryStage.setY(0);

        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
