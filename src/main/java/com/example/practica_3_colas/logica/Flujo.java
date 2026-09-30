package com.example.practica_3_colas.logica;

public class Flujo {

    private Estacion[] estaciones;

    private int ronda;
    private int siguienteIdCliente;
    private int clientesTerminados;

    public Flujo() {
        estaciones = new Estacion[9];

        for (int i =0; i < estaciones.length; i++) {
            estaciones[i] = new Estacion(i + 1, 100);
        }
        ronda = 0;
        siguienteIdCliente = 1;
        clientesTerminados = 0;
    }

    public void iniciarJuego() {

    }


    public int getRonda() {
        return ronda;
    }

    public int getClientesTerminados() {
        return clientesTerminados;
    }

    public Estacion[] getEstaciones() {
        return estaciones;
    }
}