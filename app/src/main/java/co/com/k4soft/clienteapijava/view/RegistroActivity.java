package co.com.k4soft.clienteapijava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import co.com.k4soft.clienteapijava.cliente.documento.DocumentoService;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaStrategy;
import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaService;
import co.com.k4soft.clienteapijava.model.Persona;
import co.com.k4soft.clienteapijava.model.TipoDocumento;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegistroActivity extends AppCompatActivity implements PersonaStrategy {
    EditText etNombre;
    EditText etApellido;
    Spinner spTipoDocumento;
    EditText etNumeroDocumento;

    Button btnRegistrar;



    PersonaService personaService;
    DocumentoService documentoService;

    List<TipoDocumento> listaDocumentos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initComponents();
        consultarDocumentos();
        ponerValoresSpinner();
        }



    private void initComponents() {
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        spTipoDocumento = findViewById(R.id.spTipoDocumento);
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
    private void consultarDocumentos(){
        /*
        documentoService= new DocumentoService(this);
        LiveData<List<TipoDocumento>> datos = documentoService.getDocumentos();

        datos.observe(this,documentos->{
            for(int i = 0; i < documentos.size(); i++){
                listaDocumentos.add(documentos.get(i));
            }
        });
         */

        //provicional
        TipoDocumento cedula = new TipoDocumento(1);
        cedula.setNombreDocumento("Cédula");

        TipoDocumento tarjetaIdentidad = new TipoDocumento(2);
        tarjetaIdentidad.setNombreDocumento("Tarjeta identidad");

        TipoDocumento cedulaExtranjeria = new TipoDocumento(3);
        cedulaExtranjeria.setNombreDocumento("Cédula extranjería");

        listaDocumentos.add(cedula);
        listaDocumentos.add(tarjetaIdentidad);
        listaDocumentos.add(cedulaExtranjeria);
        //fin provicional

         /*
        for (TipoDocumento documento:datos.getValue()){
            listaDocumentos.add(documento);
        }
          */

    }
    private void ponerValoresSpinner(){
        String [] opciones = {"Cédula","Tarjeta identidad","Cédula extranjería"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,opciones);
        spTipoDocumento.setAdapter(adapter);
    }
}