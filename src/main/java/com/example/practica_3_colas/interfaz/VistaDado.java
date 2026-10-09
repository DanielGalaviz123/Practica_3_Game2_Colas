package com.example.practica_3_colas.interfaz;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VistaDado extends Pane {

    private final Pane puntos;
    private double tamano;

    public VistaDado(int valor) {

        this(valor,55);
    }

    public VistaDado(int valor, double tamano) {

        this.tamano=tamano;

        setPrefSize(tamano, tamano);
        setMinSize(tamano, tamano);
        setMaxSize(tamano, tamano);

        Rectangle fondo=new Rectangle(tamano, tamano);

        fondo.setArcWidth(tamano*0.22);
        fondo.setArcHeight(tamano*0.22);
        fondo.setFill(Color.web("#e52323"));
        fondo.setStroke(Color.web("#8f1111"));
        fondo.setStrokeWidth(2);

        puntos = new Pane();
        puntos.setPrefSize(tamano, tamano);

        getChildren().addAll(fondo, puntos);
        mostrarValor(valor);
    }

    public void mostrarValor(int valor) {

        puntos.getChildren().clear();

        double izquierda=tamano*0.29;
        double centro=tamano*0.50;
        double derecha=tamano*0.71;

        double arriba=tamano*0.25;
        double medio=tamano*0.50;
        double abajo=tamano*0.75;

        switch(valor) {

            case 1:
                agregarPunto(centro, centro);
                break;

            case 2:
                agregarPunto(izquierda, arriba);
                agregarPunto(derecha, abajo);
                break;

            case 3:
                agregarPunto(izquierda, arriba);
                agregarPunto(centro, centro);
                agregarPunto(derecha, abajo);
                break;

            case 4:
                agregarPunto(izquierda, arriba);
                agregarPunto(derecha, arriba);
                agregarPunto(izquierda, abajo);
                agregarPunto(derecha, abajo);
                break;

            case 5:
                agregarPunto(izquierda, arriba);
                agregarPunto(derecha, arriba);
                agregarPunto(centro, centro);
                agregarPunto(izquierda, abajo);
                agregarPunto(derecha, abajo);
                break;

            case 6:
                agregarPunto(izquierda, arriba);
                agregarPunto(derecha, arriba);
                agregarPunto(izquierda, medio);
                agregarPunto(derecha, medio);
                agregarPunto(izquierda, abajo);
                agregarPunto(derecha, abajo);
                break;
        }
    }

    private void agregarPunto(double x, double y) {

        Circle punto=new Circle(tamano*0.09);

        punto.setCenterX(x);
        punto.setCenterY(y);
        punto.setFill(Color.WHITE);

        puntos.getChildren().add(punto);
    }
}