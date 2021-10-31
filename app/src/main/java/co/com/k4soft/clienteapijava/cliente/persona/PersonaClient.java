package co.com.k4soft.clienteapijava.cliente.persona;



import java.util.List;

import co.com.k4soft.clienteapijava.model.Persona;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PersonaClient {

    @GET("v1/persona")
    Call<List<Persona>> findAll();

    @POST("v1/persona")
    Call<Persona> crearPersona(@Body Persona persona);

}
