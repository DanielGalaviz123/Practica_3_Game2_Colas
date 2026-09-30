package com.example.practica_3_colas.logica;

import java.util.Random;

public class Dado {

    private int valor;

    public Dado() {
        valor= 1;
    }

    public int tirar() {
        Random random = new Random();
        valor = random.nextInt(6)+1;
        return valor;
    }

    public int getValor() {
        return valor;
    }
}
