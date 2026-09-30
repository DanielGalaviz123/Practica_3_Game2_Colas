package com.example.practica_3_colas.logica;

public class Cliente {

    private int id;
    private int rondaEntrada;

    public Cliente(int id, int rondaEntrada) {
        this.id =id;
        this.rondaEntrada =rondaEntrada;
    }

    public int getId() {
        return id;
    }

    public int getRondaEntrada() {
        return rondaEntrada;
    }
}