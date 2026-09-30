package com.example.practica_3_colas.interfaz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelLateral extends VBox {

    private Button tirar;
    private Label ronda;

    public PanelLateral() {

        setPrefWidth(210);
        setPadding(new Insets(30, 20, 30, 20));
        setSpacing(18);
        setAlignment(Pos.TOP_CENTER);

        setStyle("-fx-background-color: #4a2424;");

        Label titulo=new Label("ESTADISTICAS");

        titulo.setStyle("-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");

        Button actividad=crearBoton("Actividad");
        Button throughput = crearBoton("Throughput");
        Button sistema=crearBoton("Number in system");
        Button tiempo = crearBoton("Time in system");

        Label textoRonda=new Label("RONDA");

        textoRonda.setStyle("-fx-text-fill: white;" +
                        "-fx-font-size: 18px;"
        );

        ronda = new Label("0");

        ronda.setStyle("-fx-text-fill: white;" +
                        "-fx-font-size: 42px;" +
                        "-fx-font-weight: bold;"
        );

        tirar=crearBoton("TIRAR DADOS");

        tirar.setStyle("-fx-background-color: #eee8dd;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 7;");

        getChildren().addAll(
                titulo,
                actividad,
                throughput,
                sistema,
                tiempo,
                crearEspacio(),
                textoRonda,
                ronda,
                tirar
        );
    }

    private Button crearBoton(String texto) {

        Button boton=new Button(texto);

        boton.setPrefWidth(170);
        boton.setPrefHeight(50);

        boton.setStyle("-fx-background-color: #eee8dd;" +
                        "-fx-text-fill: #4a2424;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 6;"
        );

        return boton;
    }

    private Label crearEspacio() {

        Label espacio = new Label();
        espacio.setPrefHeight(35);
        return espacio;
    }

    public Button getTirar() {
        return tirar;
    }

    public Label getRonda() {
        return ronda;
    }
}