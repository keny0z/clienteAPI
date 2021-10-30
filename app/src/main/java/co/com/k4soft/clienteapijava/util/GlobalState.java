package co.com.k4soft.clienteapijava.util;

import android.app.Application;

import java.util.List;

import co.com.k4soft.clienteapijava.model.Persona;
import lombok.Data;

@Data
public class GlobalState extends Application {

    private String accessToken;

}
