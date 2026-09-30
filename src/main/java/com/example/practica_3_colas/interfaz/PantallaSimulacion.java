package com.example.practica_3_colas.interfaz;

import com.example.practica_3_colas.logica.Dado;
import javafx.scene.layout.BorderPane;

public class PantallaSimulacion extends BorderPane {

    private TableroProduccion tablero;
    private PanelLateral lateral;
    private Dado[] dados;
    private int ronda;

    public PantallaSimulacion() {

        tablero=new TableroProduccion();
        lateral = new PanelLateral();

        dados=new Dado[9];

        for(int i=0; i < dados.length; i++) {
            dados[i]=new Dado();
        }

        ronda=0;

        lateral.getTirar().setOnAction(e -> tirarDados());

        setCenter(tablero);
        setRight(lateral);
    }

    private void tirarDados() {

        VistaEstacion[] estaciones=tablero.getEstaciones();

        for(int i=0; i < dados.length; i++) {

            int valor = dados[i].tirar();
            estaciones[i].getDado().mostrarValor(valor);
        }

        ronda++;
        lateral.getRonda().setText(String.valueOf(ronda));
    }

    public TableroProduccion getTablero() {
        return tablero;
    }

    public PanelLateral getLateral() {
        return lateral;
    }
}