package com.example.practica_3_colas.interfaz;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VistaCliente extends Circle {

    public VistaCliente(boolean nuevo) {

        super(8);

        if(nuevo) {
            setFill(Color.web("#4a90e2"));
        }else{
            setFill(Color.web("#8a8a8a"));
        }

        setStroke(Color.web("#444444"));
        setStrokeWidth(1.5);
    }
}