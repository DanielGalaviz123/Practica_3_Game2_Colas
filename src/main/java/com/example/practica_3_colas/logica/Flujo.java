package com.example.practica_3_colas.logica;

import java.util.ArrayList;

public class Flujo {

    private Estacion[] estaciones;

    private int ronda;
    private int siguienteIdCliente;
    private int clientesTerminados;

    private int[][] historialRolled;
    private int[][] historialMoved;
    private int[] historialThroughput;
    private int[] historialSistema;

    private ArrayList<Integer> tiemposSistema;
    private ArrayList<Cliente> clientesTerminadosLista;
    public Flujo() {
        estaciones = new Estacion[10];

        for (int i =0; i < estaciones.length; i++) {
            estaciones[i] = new Estacion(i + 1, 100);
        }
        ronda = 0;
        siguienteIdCliente = 1;
        clientesTerminados = 0;

        historialRolled=new int[10][20];
        historialMoved=new int[10][20];

        historialThroughput=new int[20];
        historialSistema=new int[21];
        tiemposSistema=new ArrayList<>();

        clientesTerminadosLista=new ArrayList<>();
    }

    public void addInitialClients(){

        for(int i = 0 ;i < estaciones.length; i++){

            for(int j = 0; j < 4; j++){

                estaciones[i].agregarCliente(new Cliente(siguienteIdCliente,0));
                siguienteIdCliente++;
            }
        }
    }

    //specific method for station one

    public void methodStationOne(){

        int cantidad = estaciones[0].getUltimaTirada();

        for(int i = 0; i < cantidad; i++){

            estaciones[0].agregarCliente(new Cliente(siguienteIdCliente,ronda));
            siguienteIdCliente ++;
        }

        historialMoved[0][ronda-1]=cantidad;
    }

    /*
    Rules
    Check how many customers the previous station has.
    If there are more customers than the die roll total, move them all.
    Otherwise, move those that are available.
     */
    public void moveClients(int station) {

        int available = estaciones[station-1].getClientes().getCantidad();
        int wanted = estaciones[station].getUltimaTirada();

        int moved;

        if(available <= wanted){

            moved=available;

            for(int i=0; i < available; i++){

                Cliente cliente = estaciones[station-1].sacarCliente();
                estaciones[station].agregarCliente(cliente);
            }

        }else{

            moved=wanted;

            for(int i=0; i < wanted; i++){

                Cliente cliente = estaciones[station-1].sacarCliente();
                estaciones[station].agregarCliente(cliente);
            }
        }

        historialMoved[station][ronda-1]=moved;
    }

    //move special.
    public void methodLastStation(){

        int available = estaciones[9].getClientes().getCantidad();
        int wanted = estaciones[9].getUltimaTirada();

        int moved;

        if(available <= wanted){

            moved=available;

            for(int i=0; i < available; i++){

                Cliente cliente=estaciones[9].sacarCliente();
                clientesTerminados++;
                clientesTerminadosLista.add(cliente);

                if(cliente.getRondaEntrada() > 0){

                    int tiempo=ronda-cliente.getRondaEntrada()+1;
                    tiemposSistema.add(tiempo);
                }
            }

        }else{

            moved=wanted;

            for(int i=0; i < wanted; i++){

                Cliente cliente=estaciones[9].sacarCliente();
                clientesTerminados++;
                clientesTerminadosLista.add(cliente);

                if(cliente.getRondaEntrada() > 0){

                    int tiempo=ronda-cliente.getRondaEntrada()+1;
                    tiemposSistema.add(tiempo);
                }
            }
        }

        historialMoved[9][ronda-1]=moved;
    }

    public void addInitialDice(){

        for(int i=0; i < estaciones.length; i++){
            estaciones[i].agregarDado(new Dado());
        }
    }

    public void tirarTodosDados(){

        for(int i=0; i < estaciones.length; i++){
            estaciones[i].tirarDados();
            historialRolled[i][ronda-1]= estaciones[i].getUltimaTirada();
        }
    }

    public void startGame() {
        addInitialClients();
        addInitialDice();
        historialSistema[0]=getCantidadSistema();
    }

    public void tirarRonda(){
        ronda++;
        tirarTodosDados();
    }

    public void playRound() {

        methodLastStation();

        for (int i = 9; i >= 1; i--) {
            moveClients(i);
        }

        methodStationOne();
        guardarResultados();
    }

    private void guardarResultados(){

        historialThroughput[ronda-1]=clientesTerminados;
        historialSistema[ronda]=getCantidadSistema();
    }

    private int getCantidadSistema(){

        int clientesSistema=0;

        for(int i=0; i < estaciones.length; i++){
            clientesSistema= clientesSistema+estaciones[i].getClientes().getCantidad();
        }
        return clientesSistema;
    }

    public void reassignDie(int from, int to) {

        if (estaciones[from - 1].getDados().isEmpty()) {
            System.out.println("The station has no dice");
            return;
        }

        Dado dado = estaciones[from - 1].getDados().get(0);

        estaciones[from - 1].quitarDado(dado);
        estaciones[to - 1].agregarDado(dado);
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

    public int[][] getHistorialRolled() {
        return historialRolled;
    }

    public int[][] getHistorialMoved() {
        return historialMoved;
    }

    public int[] getHistorialThroughput() {
        return historialThroughput;
    }

    public int[] getHistorialSistema() {
        return historialSistema;
    }

    public ArrayList<Integer> getTiemposSistema() {
        return tiemposSistema;
    }
    public ArrayList<Cliente> getClientesTerminadosLista() {
        return clientesTerminadosLista;
    }
}