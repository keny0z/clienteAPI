package co.com.k4soft.clienteapijava.cliente.documento;

public interface DocumentoStrategy {
    void success();

    void badCredencials();

    void failture(String mensaje);
}
