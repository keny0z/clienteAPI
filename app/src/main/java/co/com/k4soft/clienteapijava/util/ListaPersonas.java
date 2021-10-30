package co.com.k4soft.clienteapijava.util;

import java.util.List;

import co.com.k4soft.clienteapijava.model.Persona;
import lombok.Data;

@Data
public class ListaPersonas {
    private static ListaPersonas instancia = new ListaPersonas();
    private List<Persona> personas;

    private ListaPersonas(){

    }
    public static ListaPersonas obtenerInstancia(){
        return instancia;
    }
}
