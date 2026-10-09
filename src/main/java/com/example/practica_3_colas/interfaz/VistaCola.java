package com.example.practica_3_colas.interfaz;

import com.example.practica_3_colas.logica.Cliente;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class VistaCola extends FlowPane {

    public VistaCola(int cantidad) {

        setHgap(3);
        setVgap(3);

        setPrefWidth(110);
        setMaxWidth(110);
        setAlignment(Pos.CENTER);

        mostrarClientes(cantidad);
    }

    public void mostrarClientes(int cantidad) {

        getChildren().clear();

        for(int i=0; i < cantidad; i++) {
            getChildren().add(new VistaCliente(false));
        }
    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {

        getChildren().clear();

        for(Cliente cliente : clientes) {
            boolean nuevo = cliente.getRondaEntrada() > 0;
            getChildren().add(new VistaCliente(nuevo));
        }
    }
}