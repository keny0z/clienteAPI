package co.com.k4soft.webserviceclient.client;

import java.util.List;

import co.com.k4soft.webserviceclient.model.Estudiante;
import co.com.k4soft.webserviceclient.util.Parametro;
import retrofit2.Call;
import retrofit2.http.GET;

interface EstudianteClient {

    @GET(Parametro.ESTUDIANTES)
    Call<List<Estudiante>> getEstudiantes();
}
