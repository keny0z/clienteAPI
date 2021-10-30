package co.com.k4soft.webserviceclient.client;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import co.com.k4soft.webserviceclient.R;
import co.com.k4soft.webserviceclient.model.Estudiante;
import co.com.k4soft.webserviceclient.util.Parametro;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstudianteServiceImpl {

    private Context context;
    MutableLiveData<List<Estudiante>> data;


    private final OkHttpClient simpleClient = new OkHttpClient.Builder()
            .readTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .connectTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .build();

    public EstudianteServiceImpl(Context context){
        this.context = context;
        data = new MutableLiveData<>();
    }

    public LiveData<List<Estudiante>> getEstudiantes(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Parametro.URL_BASE).addConverterFactory(GsonConverterFactory.create()).client(simpleClient).build();
        EstudianteClient client = retrofit.create(EstudianteClient.class);
        Call<List<Estudiante>> respuesta = client.getEstudiantes();
        respuesta.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                List<Estudiante> estudiantes = response.body();
                if(estudiantes.isEmpty()){
                    Toast.makeText(context,context.getString(R.string.sin_estudiantes),Toast.LENGTH_SHORT).show();
                }else{
                    data.setValue(estudiantes);
                }
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                Toast.makeText(context,"Falló: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }
}
