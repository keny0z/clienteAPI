package co.com.k4soft.clienteapijava;

public interface PersonaStrategy {

    void success();

    void badCredencials();

    void failture(String mensaje);
}
