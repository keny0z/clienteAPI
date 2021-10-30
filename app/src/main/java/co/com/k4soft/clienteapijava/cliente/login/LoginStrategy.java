package co.com.k4soft.clienteapijava.cliente.login;

public interface LoginStrategy {

     void success();

     void badCredencials();

     void failture(String mensaje);
}
