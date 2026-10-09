package com.example.practica_3_colas.interfaz;

import com.example.practica_3_colas.logica.Flujo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PantallaGraficas extends BorderPane {

    private Flujo flujo;
    private BarChart<String, Number> grafica;
    private XYChart.Series<String, Number> datos;
    private int playerSeleccionado=0;
    private boolean mostrarRolled=true;
    private Label promedio;
    private Button volver;

    public PantallaGraficas(String nombreGrafica, Flujo flujo) {

        this.flujo=flujo;

        setStyle("-fx-background-color: #eeeeee;");
        setPadding(new Insets(15));

        Label titulo=new Label(nombreGrafica);
        titulo.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #432525;");

        setTop(titulo);
        BorderPane.setAlignment(titulo, Pos.CENTER);


        if(nombreGrafica.equals("Actividad")){
            mostrarActividad();

        }else if(nombreGrafica.equals("Throughput")){
            mostrarThroughput();

        }else if(nombreGrafica.equals("Number in system")){
            mostrarSistema();

        }else if(nombreGrafica.equals("Time in system")){
            mostrarTiempo();

        }else if(nombreGrafica.equals("Your performance")){
            mostrarRendimiento();
        }


        volver=new Button("X");
        volver.setPrefSize(55,55);
        volver.setStyle("-fx-font-size: 24px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 30;" +
                "-fx-background-color: #d92323;" + "-fx-text-fill: white;");

        HBox parteInferior=new HBox(volver);

        parteInferior.setAlignment(Pos.CENTER_RIGHT);
        parteInferior.setPadding(new Insets(5,20,5,5));

        setBottom(parteInferior);
    }


    private void crearGrafica(String nombreEjeX, String nombreEjeY, int inicio, int fin,
                              double minimoY, double maximoY, double salto){

        CategoryAxis ejeX=new CategoryAxis();
        NumberAxis ejeY=new NumberAxis(minimoY,maximoY,salto);

        ObservableList<String> categorias=FXCollections.observableArrayList();

        for(int i=inicio; i <= fin; i++){

            categorias.add(String.valueOf(i));
        }

        ejeX.setCategories(categorias);
        ejeX.setAutoRanging(false);

        ejeX.setLabel(nombreEjeX);
        ejeY.setLabel(nombreEjeY);

        grafica=new BarChart<>(ejeX,ejeY);

        grafica.setLegendVisible(false);
        grafica.setAnimated(false);

        grafica.setBarGap(2);
        grafica.setCategoryGap(5);

        datos=new XYChart.Series<>();
        grafica.getData().add(datos);
    }


    private void mostrarActividad(){

        crearGrafica("Turn", "Number", 1,20, 0,36,2);

        VBox contenido=new VBox(10);
        contenido.setAlignment(Pos.CENTER);

        HBox modos=new HBox(10);
        modos.setAlignment(Pos.CENTER);

        Button rolled=new Button("Rolled");
        Button moved=new Button("Moved");

        rolled.setOnAction(e -> {

            mostrarRolled=true;
            actualizarActividad();
        });

        moved.setOnAction(e -> {

            mostrarRolled=false;
            actualizarActividad();
        });


        promedio=new Label();
        promedio.setStyle("-fx-font-size: 18px;" + "-fx-text-fill: #432525;" + "-fx-font-weight: bold;");
        modos.getChildren().addAll(rolled, moved, promedio);

        HBox opciones=new HBox(8);
        opciones.setAlignment(Pos.CENTER);

        for(int i=0; i < 10; i++){

            int player=i;

            Button boton=new Button(String.valueOf(i+1));

            boton.setOnAction(e -> {
                playerSeleccionado=player;
                actualizarActividad();
            });

            opciones.getChildren().add(boton);
        }


        Button todos=new Button("all");

        todos.setOnAction(e -> {
            playerSeleccionado=-1;
            actualizarActividad();
        });

        opciones.getChildren().add(todos);

        grafica.setPrefHeight(550);

        contenido.getChildren().addAll(grafica, modos, opciones);

        setCenter(contenido);
        actualizarActividad();
    }


    private void actualizarActividad(){

        datos.getData().clear();

        int[][] valores;

        if(mostrarRolled){

            valores=flujo.getHistorialRolled();
        }else{
            valores=flujo.getHistorialMoved();
        }


        double total=0;
        if(playerSeleccionado >= 0){

            for(int ronda=0; ronda < flujo.getRonda(); ronda++){

                int valor=valores[playerSeleccionado][ronda];
                datos.getData().add(new XYChart.Data<>(String.valueOf(ronda+1), valor));
                total=total+valor;
            }

        }else{

            for(int ronda=0; ronda < flujo.getRonda(); ronda++){

                double suma=0;

                for(int player=0; player < 10; player++){
                    suma=suma+valores[player][ronda];
                }

                double average=suma/10.0;

                datos.getData().add(new XYChart.Data<>(String.valueOf(ronda+1), average));
                total=total+average;
            }
        }


        if(flujo.getRonda() > 0){

            double average=total/flujo.getRonda();
            promedio.setText(String.format("Average %.1f",average));

        }else{
            promedio.setText("Average 0.0");
        }
    }


    private void mostrarThroughput(){

        crearGrafica("Turn", "Throughput", 1,20, 0,70,10);
        int[] valores=flujo.getHistorialThroughput();

        for(int i=0; i < flujo.getRonda(); i++){
            datos.getData().add(new XYChart.Data<>(String.valueOf(i+1), valores[i]));
        }

        Label total=new Label("Total en ronda 20: " + valores[19]);
        total.setStyle("-fx-font-size: 18px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #432525;");

        VBox contenido=new VBox(10);
        contenido.setAlignment(Pos.CENTER);
        contenido.getChildren().addAll(grafica, total);

        setCenter(contenido);
    }


    private void mostrarSistema(){

        crearGrafica("Turn", "Number in system", 0,20, 0,100,10);

        int[] valores=flujo.getHistorialSistema();

        for(int i=0; i <= flujo.getRonda(); i++){
            datos.getData().add(new XYChart.Data<>(String.valueOf(i), valores[i]));}
        setCenter(grafica);
    }


    private void mostrarTiempo(){

        int cantidad=flujo.getTiemposSistema().size();
        int limite=34;

        if(cantidad > limite){
            limite=cantidad;
        }

        crearGrafica("Order of arrival", "Time in system", 1,limite,
                9,20,1);

        for(int i=0; i < flujo.getTiemposSistema().size(); i++){
            datos.getData().add(new XYChart.Data<>(String.valueOf(i+1), flujo.getTiemposSistema().get(i)));
        }
        setCenter(grafica);
    }


    private void mostrarRendimiento(){

        CategoryAxis ejeX=new CategoryAxis();
        NumberAxis ejeY=new NumberAxis(0,100,10);

        ObservableList<String> categorias=FXCollections.observableArrayList();

        for(int i=30; i <= 70; i++){
            categorias.add(String.valueOf(i));
        }

        ejeX.setCategories(categorias);
        ejeX.setAutoRanging(false);

        BarChart<String,Number> graficaRendimiento = new BarChart<>(ejeX,ejeY);

        graficaRendimiento.setLegendVisible(false);
        graficaRendimiento.setAnimated(false);

        graficaRendimiento.setCategoryGap(2);
        graficaRendimiento.setBarGap(0);

        XYChart.Series<String,Number> serie = new XYChart.Series<>();

        double[] porcentajes={100,100,100,100,100,100,100,100,100,100, 100,100,100,
                99,98,97,95,92,87,81, 72,62,51,40,28,19,12,6,3,1,0.5,
                0,0,0,0,0,0,0,0,0,0
        };

        int resultado=flujo.getClientesTerminados();

        for(int i=30; i <= 70; i++){

            double valor=porcentajes[i-30];
            XYChart.Data<String,Number> dato = new XYChart.Data<>(String.valueOf(i),valor);
            serie.getData().add(dato);
        }

        graficaRendimiento.getData().add(serie);
        graficaRendimiento.setPrefHeight(560);

        Platform.runLater(() -> {

            for(int i=0; i < serie.getData().size(); i++){

                XYChart.Data<String,Number> dato=serie.getData().get(i);
                int valorX=Integer.parseInt(dato.getXValue());

                if(dato.getNode()!=null){

                    if(valorX==resultado){
                        dato.getNode().setStyle("-fx-bar-fill: #d92323;");
                    }else{
                        dato.getNode().setStyle("-fx-bar-fill: #8fb3e8;");
                    }
                }
            }
        });

        setCenter(graficaRendimiento);
    }


    public Button getVolver() {return volver;}
}