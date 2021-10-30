package co.com.k4soft.clienteapijava;

public interface LoginStrategy {

     void success();

     void badCredencials();

     void failture(String mensaje);
}
