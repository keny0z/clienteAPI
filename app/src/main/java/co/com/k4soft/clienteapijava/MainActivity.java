package co.com.k4soft.clienteapijava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.com.k4soft.clienteapijava.cliente.Service;
import co.com.k4soft.clienteapijava.cliente.impl.LoginService;
import co.com.k4soft.clienteapijava.view.ListadoActivity;


public class MainActivity extends AppCompatActivity implements LoginStrategy {


    EditText txtUser;
    EditText txtPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        btnLogin.setOnClickListener( v-> consumirServicio());
    }

    private void consumirServicio() {
        Service login = new LoginService(this,txtUser.getText().toString(),txtPassword.getText().toString());
        login.consumirServicio();
    }

    private void initComponents() {
        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void success() {
       Intent intent = new Intent(this, ListadoActivity.class);
       startActivity(intent);
    }

    @Override
    public void badCredencials() {
        Toast.makeText(this, getString(R.string.bad_credencials), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failture(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}