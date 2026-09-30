package com.example.practica_3_colas.interfaz;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaDado extends Pane {

    private final Pane puntos;

    public VistaDado(int valor) {

        setPrefSize(55, 55);
        setMinSize(55, 55);
        setMaxSize(55, 55);

        Rectangle fondo=new Rectangle(55, 55);

        fondo.setArcWidth(12);
        fondo.setArcHeight(12);
        fondo.setFill(Color.web("#e52323"));
        fondo.setStroke(Color.web("#8f1111"));
        fondo.setStrokeWidth(2);

        puntos = new Pane();
        puntos.setPrefSize(55, 55);

        getChildren().addAll(fondo, puntos);
        mostrarValor(valor);
    }

    public void mostrarValor(int valor) {

        puntos.getChildren().clear();

        switch(valor) {

            case 1:
                agregarPunto(27.5, 27.5);
                break;

            case 2:
                agregarPunto(16, 16);
                agregarPunto(39, 39);
                break;

            case 3:
                agregarPunto(16, 16);
                agregarPunto(27.5, 27.5);
                agregarPunto(39, 39);
                break;

            case 4:
                agregarPunto(16, 16);
                agregarPunto(39, 16);
                agregarPunto(16, 39);
                agregarPunto(39, 39);
                break;

            case 5:
                agregarPunto(16, 16);
                agregarPunto(39, 16);
                agregarPunto(27.5, 27.5);
                agregarPunto(16, 39);
                agregarPunto(39, 39);
                break;

            case 6:
                agregarPunto(16, 14);
                agregarPunto(39, 14);
                agregarPunto(16, 27.5);
                agregarPunto(39, 27.5);
                agregarPunto(16, 41);
                agregarPunto(39, 41);
                break;
        }
    }

    private void agregarPunto(double x, double y) {

        Circle punto=new Circle(5);
        punto.setCenterX(x);
        punto.setCenterY(y);
        punto.setFill(Color.WHITE);
        puntos.getChildren().add(punto);
    }
}