package com.example.practica_3_colas.interfaz;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PantallaGraficas extends BorderPane {

    public PantallaGraficas() {

        setStyle("-fx-background-color: #f5f1e9;");

        Label titulo=new Label("Resultados de la simulacion");

        titulo.setStyle("-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #432525;"
        );

        setTop(titulo);

    }
}