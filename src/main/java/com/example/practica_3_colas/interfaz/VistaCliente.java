package com.example.practica_3_colas.interfaz;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VistaCliente extends Circle {

    public VistaCliente() {

        super(8);

        setFill(Color.web("#8a8a8a"));
        setStroke(Color.web("#444444"));
        setStrokeWidth(1.5);
    }
}