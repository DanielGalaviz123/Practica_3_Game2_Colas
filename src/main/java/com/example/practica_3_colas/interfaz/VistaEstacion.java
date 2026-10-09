package com.example.practica_3_colas.interfaz;

import com.example.practica_3_colas.logica.Dado;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class VistaEstacion extends VBox {

    private final HBox dados;
    private final GridPane cuadriculaDados;
    private final Label persona;

    public VistaEstacion(String nombre, int valorDado) {

        setAlignment(Pos.CENTER);
        setSpacing(5);

        setPrefWidth(130);
        setMinWidth(130);
        setMaxWidth(130);
        //"🏭"
        //"👤"
        persona = new Label("🏭");
        persona.setStyle("-fx-font-size: 45px;");

        dados=new HBox();
        dados.setAlignment(Pos.CENTER);

        dados.setPrefSize(115,85);
        dados.setMinSize(115,85);
        dados.setMaxSize(115,85);

        cuadriculaDados=new GridPane();
        cuadriculaDados.setAlignment(Pos.CENTER);
        cuadriculaDados.setHgap(3);
        cuadriculaDados.setVgap(3);

        dados.getChildren().add(cuadriculaDados);

        VistaDado dado=new VistaDado(valorDado);
        cuadriculaDados.add(dado,0,0);

        Label nombreEstacion=new Label(nombre);

        nombreEstacion.setStyle("-fx-font-size: 14px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #432525;");

        getChildren().addAll(persona, dados, nombreEstacion);
    }

    public void mostrarDados(ArrayList<Dado> listaDados){

        cuadriculaDados.getChildren().clear();

        int cantidad=listaDados.size();

        double tamano;
        int columnas;

        if(cantidad <= 2){

            tamano=55;
            columnas=2;

        }else if(cantidad <= 4){

            tamano=40;
            columnas=2;

        }else{

            tamano=25;
            columnas=4;
        }

        for(int i=0; i < cantidad; i++){

            int columna=i%columnas;
            int fila=i/columnas;

            VistaDado dado= new VistaDado(listaDados.get(i).getValor(),tamano);

            cuadriculaDados.add(dado,columna,fila);
        }
    }

    public void seleccionar(boolean seleccionada) {

        if(seleccionada){
            setStyle("-fx-border-color: #e52323;" + "-fx-border-width: 3;");
        }else{
            setStyle("");
        }
    }

    public HBox getDados() {
        return dados;
    }
}