package com.example.practica_3_colas.logica;

import java.util.ArrayList;

public class Estacion {

    private int numero;
    private Cola<Cliente> clientes;
    private ArrayList<Dado> dados;
    private int ultimaTirada;

    public Estacion(int numero, int capacidadCola) {

        this.numero = numero;
        clientes = new Cola<>(capacidadCola);
        dados = new ArrayList<>();
        ultimaTirada = 0;

    }

    public void agregarCliente(Cliente cliente) {
        clientes.insertar(cliente);
    }

    public Cliente sacarCliente() {

        if(clientes.isVacia()){

            System.out.println("Cola esta vaca, Subdesbordamiento");
            return null;
        }

        Cliente dato = clientes.peek();
        clientes.eliminar();

        return dato;

    }

    public void agregarDado(Dado dado) {
        dados.add(dado);
    }

    public void quitarDado(Dado dado) {
        dados.remove(dado);
    }

    public int tirarDados() {

        int cantidad;
        int total=0;

        if(dados != null && dados.isEmpty()){

            ultimaTirada=0;
            System.out.println("No se encuentra ningun dado en la estacion");
            return 0;
        }
        for(int i = 0; i < dados.size(); i++){

           cantidad = dados.get(i).tirar();
           total = total + cantidad;
        }

        ultimaTirada = total;
        return total;
    }

    public int getUltimaTirada() {
        return ultimaTirada;
    }
    public int getNumero() {
        return numero;
    }

    public Cola<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Dado> getDados() {
        return dados;
    }
}