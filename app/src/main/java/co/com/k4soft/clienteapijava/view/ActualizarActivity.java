package co.com.k4soft.clienteapijava.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaService;
import co.com.k4soft.clienteapijava.model.Persona;

public class ActualizarActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etApellido;
    Spinner spTipoDocumento;
    EditText etNumeroDocumento;

    Button btnActualizar;
    Button btnEliminar;

    Persona persona;
    PersonaService personaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        initComponents();
        etNombre.setText(persona.getNombre());
        etApellido.setText(persona.getApellido());
        //spTipoDocumento
        etNumeroDocumento.setText(persona.getNumeroDocumento());
        ponerValoresSpinner();
        //int indice = persona.getIdTipoDocumento()-1;
        //spTipoDocumento.setSelection(indice); //Es necesario restar una unidad debido a que los indices de los vectores comienzan en 0
        btnEliminar.setOnClickListener(v -> {
            eliminarPersona(persona);
        });



    }

    private void initComponents(){
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        spTipoDocumento = findViewById(R.id.spTipoDocumento);
        etNumeroDocumento = findViewById(R.id.etNumeroDocumento);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        persona = (Persona) getIntent().getExtras().getSerializable("persona");
    }

    private void ponerValoresSpinner(){
        String [] opciones = {"Cédula","Tarjeta identidad","Cédula extranjería"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,opciones);
        spTipoDocumento.setAdapter(adapter);
    }

    private void eliminarPersona(Persona persona){
        personaService= new PersonaService(this);
        personaService.eliminarPersona(persona);
    }



}