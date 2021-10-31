package co.com.k4soft.clienteapijava.cliente.documento;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import co.com.k4soft.clienteapijava.R;
import co.com.k4soft.clienteapijava.cliente.persona.PersonaClient;
import co.com.k4soft.clienteapijava.cliente.util.RetrofitFactory;
import co.com.k4soft.clienteapijava.model.Persona;
import co.com.k4soft.clienteapijava.model.TipoDocumento;
import co.com.k4soft.clienteapijava.util.Parametro;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DocumentoService extends RetrofitFactory {
    private Context context;
    MutableLiveData<List<TipoDocumento>> data;


    private final OkHttpClient simpleClient = new OkHttpClient.Builder()
            .readTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .connectTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .build();

    public DocumentoService(Context context) {
        super(context);
        this.context = context;
        data = new MutableLiveData<>();
    }


    public LiveData<List<TipoDocumento>> getDocumentos(){

        Retrofit retrofit = getTokenInstance();

        DocumentoClient client = retrofit.create(DocumentoClient.class);
        Call<List<TipoDocumento>> respuesta = client.findAll();
        respuesta.enqueue(new Callback<List<TipoDocumento>>() {
            @Override
            public void onResponse(Call<List<TipoDocumento>> call, Response<List<TipoDocumento>> response) {
                List<TipoDocumento> documentos = response.body();
                if(documentos.isEmpty()){
                    Toast.makeText(context,context.getString(R.string.sin_personas),Toast.LENGTH_SHORT).show();
                }else{
                    data.setValue(documentos);
                }
            }

            @Override
            public void onFailure(Call<List<TipoDocumento>> call, Throwable t) {
                Toast.makeText(context,"Falló: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }
}
