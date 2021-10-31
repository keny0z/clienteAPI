package co.com.k4soft.clienteapijava.cliente.persona;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.cliente.util.RetrofitFactory;
import co.com.k4soft.clienteapijava.model.Persona;
import co.com.k4soft.clienteapijava.model.TipoDocumento;
import co.com.k4soft.clienteapijava.util.Parametro;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonaService extends RetrofitFactory {
    private Context context;
    MutableLiveData<List<Persona>> data;


    private final OkHttpClient simpleClient = new OkHttpClient.Builder()
            .readTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .connectTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .build();

    public PersonaService(Context context) {
        super(context);
        this.context = context;
        data = new MutableLiveData<>();
    }


    public LiveData<List<Persona>> getPersonas(){

        Retrofit retrofit = getTokenInstance();

        PersonaClient client = retrofit.create(PersonaClient.class);
        Call<List<Persona>> respuesta = client.findAll();
        respuesta.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                List<Persona> personas = response.body();
                if(personas.isEmpty()){
                    Toast.makeText(context,context.getString(R.string.sin_personas),Toast.LENGTH_SHORT).show();
                }else{
                    data.setValue(personas);
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                Toast.makeText(context,"Falló: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public void crearPersona(){
        //TipoDocumento documento =new TipoDocumento(1);
        //documento.setNombreDocumento("Cédula");

        Persona persona = new Persona(9,"28293",1,"sidio","nisio",true);
        Retrofit retrofit = getTokenInstance();
        PersonaClient client = retrofit.create(PersonaClient.class);
        Call<Persona> respuesta =client.crearPersona(persona);
        respuesta.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                Toast.makeText(context,"funciono!!!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Toast.makeText(context,"Falló: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }




}
