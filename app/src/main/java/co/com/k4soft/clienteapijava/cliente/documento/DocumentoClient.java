package co.com.k4soft.clienteapijava.cliente.documento;

import java.util.List;

import co.com.k4soft.clienteapijava.model.TipoDocumento;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DocumentoClient {
    @GET("v1/tipo-documento")
    Call<List<TipoDocumento>> findAll();
}
