package co.com.k4soft.clienteapijava.model;

import lombok.Data;

@Data
public class TipoDocumento {

    private Integer idTipoDocumento;
    private String nombreDocumento;

    public TipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;

    }
}
