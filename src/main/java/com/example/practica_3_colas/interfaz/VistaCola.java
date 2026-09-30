package com.example.practica_3_colas.interfaz;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class VistaCola extends FlowPane {

    public VistaCola(int cantidad) {

        setHgap(3);
        setVgap(3);

        setPrefWidth(110);
        setMaxWidth(110);
        setAlignment(Pos.CENTER);

        for(int i=0; i < cantidad; i++) {

            getChildren().add(new VistaCliente());
        }
    }
}