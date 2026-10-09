package com.example.practica_3_colas.interfaz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelLateral extends VBox {

    private Button actividad;
    private Button throughput;
    private Button sistema;
    private Button tiempo;
    private Button rendimiento;
    private Button tirar;
    private Label ronda;

    public PanelLateral() {

        setPrefWidth(210);
        setPadding(new Insets(30, 20, 30, 20));
        setSpacing(18);
        setAlignment(Pos.TOP_CENTER);

        setStyle("-fx-background-color: #000000;");

        Label titulo=new Label("STATISTICS");
        titulo.setStyle("-fx-text-fill: white;" + "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;");

        actividad = crearBoton("Activity");
        throughput = crearBoton("Throughput");
        sistema = crearBoton("Number in system");
        tiempo = crearBoton("Time in system");
        rendimiento = crearBoton("Your performance");
        rendimiento.setVisible(false);
        rendimiento.setManaged(false);

        Label textoRonda=new Label("RONDA");
        textoRonda.setStyle("-fx-text-fill: white;" + "-fx-font-size: 18px;");

        ronda = new Label("0");
        ronda.setStyle("-fx-text-fill: white;" + "-fx-font-size: 42px;" + "-fx-font-weight: bold;");

        tirar=crearBoton("TIRAR DADOS");
        tirar.setStyle("-fx-background-color: #eeeeee;" + "-fx-text-fill: #333333;" + "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" + "-fx-background-radius: 7;");

        getChildren().addAll(titulo, actividad, throughput, sistema,
                tiempo,
                rendimiento,
                crearEspacio(),
                textoRonda,
                ronda,
                tirar);
    }

    private Button crearBoton(String texto) {
        Button boton=new Button(texto);
        boton.setPrefWidth(170);
        boton.setPrefHeight(50);
        boton.setStyle("-fx-background-color: #d92323;" + "-fx-text-fill: white;" + "-fx-font-size: 16px;"
                + "-fx-background-radius: 7;");
        return boton;
    }

    private Label crearEspacio() {
        Label espacio=new Label();
        espacio.setPrefHeight(10);
        return espacio;
    }

    public Button getTirar() {
        return tirar;
    }

    public Label getRonda() {
        return ronda;
    }

    public Button getActividad() {
        return actividad;
    }

    public Button getThroughput() {
        return throughput;
    }

    public Button getSistema() {
        return sistema;
    }

    public Button getTiempo() {
        return tiempo;
    }

    public Button getRendimiento() {
        return rendimiento;
    }
}