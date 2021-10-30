package co.com.k4soft.webserviceclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.com.k4soft.webserviceclient.client.EstudianteServiceImpl;
import co.com.k4soft.webserviceclient.dialog.CustomDialog;
import co.com.k4soft.webserviceclient.dialog.LoadingDialog;
import co.com.k4soft.webserviceclient.model.Estudiante;

public class MainActivity extends AppCompatActivity {

    ListView listViewEstudiantes;
    EstudianteServiceImpl estudianteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewEstudiantes = findViewById(R.id.listViewEstudiantes);
        consumirServicio();
    }

    private void consumirServicio() {
        LoadingDialog loadingDialog = CustomDialog.showLoading(this,getString(R.string.cargando_estudiantes));
        estudianteService = new EstudianteServiceImpl(this);
        LiveData<List<Estudiante>> datos = estudianteService.getEstudiantes();
        datos.observe(this,estudiantes->{
               List<String> arrayEstudiantes = new ArrayList<>(estudiantes.size());

               for(int i = 0; i < estudiantes.size(); i++){
                   StringBuffer cadenaEstudiante = new StringBuffer();
                   cadenaEstudiante.append(estudiantes.get(i).getIdEstudiante())
                           .append(" ")
                           .append(estudiantes.get(i).getNombres())
                           .append(" ")
                           .append(estudiantes.get(i).getApellidos());
                   arrayEstudiantes.add(cadenaEstudiante.toString());
               }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arrayEstudiantes);
               listViewEstudiantes.setAdapter(arrayAdapter);
            loadingDialog.dismiss();
            CustomDialog.okToast(getApplicationContext(),getString(R.string.success_comunication));
        });
    }
}