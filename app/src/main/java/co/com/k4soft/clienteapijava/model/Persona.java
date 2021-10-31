package co.com.k4soft.clienteapijava.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Persona implements Serializable {

    private Integer idPersona;
    private String numeroDocumento;
    private Integer idTipoDocumento;
    private String nombre;
    private String apellido;
    private boolean activo;

    public Persona(Integer idPersona, String numeroDocumento, Integer idTipoDocumento, String nombre, String apellido, boolean activo) {
        this.idPersona = idPersona;
        this.numeroDocumento = numeroDocumento;
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.activo = activo;
    }
}
