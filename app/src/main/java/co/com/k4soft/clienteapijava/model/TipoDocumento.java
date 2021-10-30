package co.com.k4soft.clienteapijava.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TipoDocumento implements Serializable {

    private Integer idTipoDocumento;
    private String nombreDocumento;

    public TipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;

    }
}
