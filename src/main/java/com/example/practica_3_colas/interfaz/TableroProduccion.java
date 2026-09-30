package com.example.practica_3_colas.interfaz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class TableroProduccion extends GridPane {

    private VistaEstacion[] estaciones;

    public TableroProduccion() {

        setPadding(new Insets(15));
        setHgap(20);
        setVgap(25);
        setAlignment(Pos.CENTER);

        setStyle("-fx-background-color: #e8e2d8;" + "-fx-border-color: #aaa399;" + "-fx-border-width: 2;");

        estaciones=new VistaEstacion[9];

        estaciones[0] = new VistaEstacion("Estacion 1", 3);
        estaciones[1]=new VistaEstacion("Estacion 2", 5);
        estaciones[2] = new VistaEstacion("Estacion 3", 5);
        estaciones[3]=new VistaEstacion("Estacion 4", 4);

        estaciones[4] = new VistaEstacion("Estacion 5", 2);
        estaciones[5]=new VistaEstacion("Estacion 6", 1);

        estaciones[6] = new VistaEstacion("Estacion 7", 1);
        estaciones[7]=new VistaEstacion("Estacion 8", 1);
        estaciones[8] = new VistaEstacion("Estacion 9", 4);


        // parte superior
        add(estaciones[0], 0, 0);
        add(estaciones[1], 2, 0);
        add(estaciones[2], 4, 0);
        add(estaciones[3], 6, 0);


        // lado derecho
        add(estaciones[4], 7, 1);
        add(estaciones[5], 7, 2);


        // parte inferior
        add(estaciones[6], 0, 3);
        add(estaciones[7], 2, 3);
        add(estaciones[8], 4, 3);


        for(int i=0; i < 8; i++) {

            ColumnConstraints columna= new ColumnConstraints();

            columna.setMinWidth(90);
            columna.setPrefWidth(115);
            getColumnConstraints().add(columna);
        }

        for(int i=0; i < 4; i++) {

            RowConstraints fila= new RowConstraints();

            fila.setMinHeight(130);
            fila.setPrefHeight(150);
            getRowConstraints().add(fila);
        }
    }

    public VistaEstacion[] getEstaciones() {
        return estaciones;
    }
}