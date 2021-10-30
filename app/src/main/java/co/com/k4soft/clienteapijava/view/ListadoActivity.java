package co.com.k4soft.clienteapijava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.com.k4soft.clienteapijava.PersonaStrategy;
import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.adapter.PersonaBaseAdapter;
import co.com.k4soft.clienteapijava.cliente.Service;
import co.com.k4soft.clienteapijava.cliente.impl.LoginService;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaClient;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaService;
import co.com.k4soft.clienteapijava.model.Persona;
import co.com.k4soft.clienteapijava.model.TipoDocumento;
import co.com.k4soft.clienteapijava.util.GlobalState;
import co.com.k4soft.clienteapijava.util.ListaPersonas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListadoActivity extends AppCompatActivity implements PersonaStrategy {

    //private ListaPersonas listaPersonas = ListaPersonas.obtenerInstancia();
    private List<Persona> listaPersonas = new ArrayList<>();




    ListView listViewPersonas;

    EditText txtBusqueda;
    PersonaBaseAdapter personaBaseAdapter;
    PersonaService personaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        initComponents();
        cargarPersonas();
        cargarAdapter();



        txtBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                personaBaseAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void cargarAdapter() {
        personaBaseAdapter = new PersonaBaseAdapter(this,listaPersonas);
        listViewPersonas.setAdapter(personaBaseAdapter);
    }

    private void initComponents() {
        listViewPersonas = findViewById(R.id.listViewPersonas);
        txtBusqueda = findViewById(R.id.txtBusqueda);
    }

    private void cargarPersonas() {
        consumirServicio();


        //TipoDocumento documento = new TipoDocumento(1);

        //listaPersonas.add(new Persona(1,"1007566034",documento,"kevin","ospina",true));
        //listaPersonas.add(new Persona(2,"1007566036",documento,"tomas","garcia",true));

    }

    private void consumirServicio() {
        personaService= new PersonaService(this);
        LiveData<List<Persona>> datos = personaService.getPersonas();
        datos.observe(this,personas->{
            for(int i = 0; i < personas.size(); i++){
                listaPersonas.add(personas.get(i));
            }

        });


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
}