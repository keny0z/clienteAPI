package co.com.k4soft.clienteapijava.cliente.persona;

public interface PersonaStrategy {

    void success();

    void badCredencials();

    void failture(String mensaje);
}
