package com.example.practica_3_colas.interfaz;

import com.example.practica_3_colas.logica.Flujo;
import javafx.scene.layout.BorderPane;

public class PantallaSimulacion extends BorderPane {

    private TableroProduccion tablero;
    private PanelLateral lateral;
    private Flujo flujo;

    private int estacionSeleccionada=-1;
    private boolean esperandoRoll=false;
    private boolean juegoTerminado=false;

    public PantallaSimulacion() {

        tablero=new TableroProduccion();
        lateral = new PanelLateral();

        flujo = new Flujo();
        flujo.startGame();

        actualizarInterfaz();
        configurarMovimientoDados();

        lateral.getTirar().setOnAction(e -> tirarDados());
        lateral.getActividad().setOnAction(e -> mostrarGrafica("Actividad"));
        lateral.getThroughput().setOnAction(e -> mostrarGrafica("Throughput"));
        lateral.getSistema().setOnAction(e -> mostrarGrafica("Number in system"));
        lateral.getTiempo().setOnAction(e -> mostrarGrafica("Time in system"));
        lateral.getRendimiento().setOnAction(e -> mostrarGrafica("Your performance"));

        setCenter(tablero);
        setRight(lateral);
    }

    private void tirarDados() {

        if(juegoTerminado){
            reiniciarJuego();
            return;
        }

        if(!esperandoRoll){

            flujo.tirarRonda();

            actualizarInterfaz();
            lateral.getRonda().setText(String.valueOf(flujo.getRonda()));

            lateral.getTirar().setText("ROLL");
            esperandoRoll=true;

        }else{

            flujo.playRound();

            actualizarInterfaz();

            if(flujo.getRonda() == 20){

                lateral.getTirar().setText("REINICIAR");
                juegoTerminado=true;

                lateral.getRendimiento().setVisible(true);
                lateral.getRendimiento().setManaged(true);

            }else{

                lateral.getTirar().setText("TIRAR DADOS");
            }

            esperandoRoll=false;
        }
    }

    private void reiniciarJuego(){

        flujo=new Flujo();
        flujo.startGame();

        estacionSeleccionada=-1;
        esperandoRoll=false;
        juegoTerminado=false;

        lateral.getRonda().setText("0");
        lateral.getTirar().setText("TIRAR DADOS");

        lateral.getRendimiento().setVisible(false);
        lateral.getRendimiento().setManaged(false);

        actualizarInterfaz();
    }

    private void configurarMovimientoDados(){

        VistaEstacion[] vistas=tablero.getEstaciones();

        for(int i=0; i < vistas.length; i++){

            int estacion=i;

            vistas[i].setOnMouseClicked(e -> seleccionarEstacion(estacion));
        }
    }

    private void seleccionarEstacion(int estacion){

        if(esperandoRoll || juegoTerminado){
            return;
        }

        VistaEstacion[] vistas=tablero.getEstaciones();

        if(estacionSeleccionada == -1){

            if(flujo.getEstaciones()[estacion].getDados().isEmpty()){
                return;
            }

            estacionSeleccionada=estacion;
            vistas[estacion].seleccionar(true);

        }else{

            vistas[estacionSeleccionada].seleccionar(false);

            if(estacionSeleccionada != estacion){

                flujo.reassignDie(estacionSeleccionada + 1, estacion + 1);
                actualizarInterfaz();
            }

            estacionSeleccionada=-1;
        }
    }

    private void mostrarGrafica(String nombreGrafica) {

        PantallaGraficas pantalla=new PantallaGraficas(nombreGrafica, flujo);

        pantalla.getVolver().setOnAction(e -> {
            setCenter(tablero);
            setRight(lateral);
        });

        setCenter(pantalla);
        setRight(null);
    }

    private void actualizarInterfaz() {

        VistaEstacion[] vistas=tablero.getEstaciones();
        VistaCola[] colas=tablero.getColas();

        for(int i=0; i < vistas.length; i++) {

            colas[i].mostrarClientes(flujo.getEstaciones()[i].getClientes().getElementos());
            vistas[i].mostrarDados(flujo.getEstaciones()[i].getDados());
        }

        tablero.getTerminados().mostrarClientes(flujo.getClientesTerminadosLista());
    }

    public TableroProduccion getTablero() {
        return tablero;
    }

    public PanelLateral getLateral() {
        return lateral;
    }
}