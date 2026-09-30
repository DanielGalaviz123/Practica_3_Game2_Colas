package com.example.practica_3_colas.interfaz;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaEstacion extends VBox {

    private final VistaDado dado;

    public VistaEstacion(String nombre, int valorDado) {

        setAlignment(Pos.CENTER);
        setSpacing(5);

        Label persona=new Label("👤");
        persona.setStyle("-fx-font-size: 45px;");

        dado = new VistaDado(valorDado);

        Label nombreEstacion=new Label(nombre);

        nombreEstacion.setStyle("-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #432525;");

        getChildren().addAll(
                persona,
                dado,
                nombreEstacion
        );
    }

    public VistaDado getDado() {
        return dado;
    }
}