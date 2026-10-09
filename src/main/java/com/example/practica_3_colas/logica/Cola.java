package com.example.practica_3_colas.logica;

import java.util.ArrayList;
public class Cola<T> {

    private T[] cola;
    private int inicio;
    private int fin;

    public Cola(int capacidad) {
        cola = (T[]) new Object[capacidad];
        inicio = -1;
        fin = -1;
    }

    public boolean isVacia() {
        return inicio == -1;
    }

    public boolean isLlena() {
        return fin == cola.length - 1;
    }

    public void insertar(T dato) {

        if (fin < cola.length - 1) {

            fin = fin + 1;
            cola[fin] = dato;

            if (fin == 0) {
                inicio = 0;
            }

        } else {
            System.out.println("Desbordamiento");
        }
    }

    public T eliminar() {

        if (inicio != -1) {

            T dato = cola[inicio];

            if (inicio == fin) {
                inicio = -1;
                fin = -1;
            } else {
                inicio++;
            }

            return dato;
        }

        return null;
    }

    public T peek() {

        if (inicio != -1) {
            return cola[inicio];
        }

        return null;
    }

    public int getCantidad() {

        if (inicio == -1) {
            return 0;
        }

        return fin - inicio + 1;
    }
    public ArrayList<T> getElementos() {

        ArrayList<T> elementos = new ArrayList<>();

        if(inicio == -1) {
            return elementos;
        }

        for(int i = inicio; i <= fin; i++) {
            elementos.add(cola[i]);
        }

        return elementos;
    }
}