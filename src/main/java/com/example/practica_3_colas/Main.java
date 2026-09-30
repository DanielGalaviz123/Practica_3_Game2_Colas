package com.example.practica_3_colas;

import com.example.practica_3_colas.interfaz.PantallaSimulacion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        PantallaSimulacion pantalla=new PantallaSimulacion();

        Scene scene=new Scene(pantalla, 1400, 800);

        stage.setTitle("Practica 3 - Colas");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}