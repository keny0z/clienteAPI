package co.com.k4soft.clienteapijava.view;

import androidx.appcompat.app.AppCompatActivity;

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
        btnRegistrar.setOnClickListener(v -> registrarPersona());
        }



    private void initComponents() {
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        spTipoDocumento = findViewById(R.id.spTipoDocumento);
        etNumeroDocumento = findViewById(R.id.etNumeroDocumento);
        btnRegistrar = findViewById(R.id.btnActualizar);


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

    private void registrarPersona() {
        personaService= new PersonaService(this);
        TipoDocumento documento = crearTipoDocumento();
        Persona persona = new Persona(etNumeroDocumento.getText().toString(),documento.getIdTipoDocumento(),etNombre.getText().toString(),etApellido.getText().toString(),true);
        personaService.crearPersona(persona);


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
    private TipoDocumento crearTipoDocumento(){
        TipoDocumento documento = new TipoDocumento(1);
        String documentoSeleccionado = spTipoDocumento.getSelectedItem().toString();
        if(documentoSeleccionado.equals("Cédula")){
            documento.setIdTipoDocumento(1);
            documento.setNombreDocumento("Cédula");
        }else if(documentoSeleccionado.equals("Tarjeta identidad")){
            documento.setIdTipoDocumento(2);
            documento.setNombreDocumento("Tarjeta identidad");
        }else if(documentoSeleccionado.equals("Cédula extranjería")){
            documento.setIdTipoDocumento(3);
            documento.setNombreDocumento("Cédula extranjería");
        }
        return documento;
    }
}