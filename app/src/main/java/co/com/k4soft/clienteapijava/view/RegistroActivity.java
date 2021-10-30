package co.com.k4soft.clienteapijava.view;

import androidx.appcompat.app.AppCompatActivity;

import co.com.k4soft.clienteapijava.cliente.persona.PersonaStrategy;
import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaService;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity implements PersonaStrategy {
    EditText etNombre;
    EditText etApellido;
    EditText etTipoDocumento;
    EditText etNumeroDocumento;
    Button btnRegistrar;

    PersonaService personaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initComponents();

    }
    private void initComponents() {
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTipoDocumento = findViewById(R.id.etTipoDocumento);
        etNumeroDocumento = findViewById(R.id.etNumeroDocumento);
        btnRegistrar = findViewById(R.id.btnRegistrar);
    }

    @Override
    public void success() {

    }

    @Override
    public void badCredencials() {

    }

    @Override
    public void failture(String mensaje) {

    }

    private void consumirServicio() {
        personaService= new PersonaService(this);



    }
}