package co.com.k4soft.clienteapijava.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.model.Persona;

public class ActualizarActivity extends AppCompatActivity {

    private Persona persona;
    private TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        initComponents();
        txtNombre.setText(persona.getNombre());


    }

    private void initComponents(){
        txtNombre = findViewById(R.id.txtNombre);
        persona = (Persona) getIntent().getExtras().getSerializable("persona");
    }


}