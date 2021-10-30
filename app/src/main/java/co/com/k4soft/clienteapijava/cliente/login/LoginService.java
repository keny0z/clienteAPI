package co.com.k4soft.clienteapijava.cliente.login;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import co.com.k4soft.clienteapijava.cliente.Service;
import co.com.k4soft.clienteapijava.cliente.util.RetrofitFactory;
import co.com.k4soft.clienteapijava.model.security.LoginResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginService  extends RetrofitFactory implements Service{

    private String credentials =  "cliente-app" + ":" + "abcde*";
    final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

    private Context context;
    private final String user;
    private final String password;

    public LoginService(Context context, String user, String password) {
        super(context);
        this.context = context;
        this.user = user;
        this.password = password;
    }

    @Override
    public void consumirServicio() {
        Retrofit retrofit = getAuthInstance();
        LoginClient client = retrofit.create(LoginClient.class);
        Call<ResponseBody> response = client.login(basic, user, password,"password");
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if(responseBody != null){
                    Gson gson = new Gson();
                    try {
                        LoginResponse loginResponse = gson.fromJson(responseBody.string(), (Type) LoginResponse.class);
                        getGlobalState().setAccessToken(loginResponse.getAccess_token());
                        ((LoginStrategy) context).success();
                    }catch (Exception e){
                        ((LoginStrategy) context).failture(e.getMessage());
                    }
                }else {
                    ((LoginStrategy) context).badCredencials();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ((LoginStrategy) context).failture(t.getMessage());
            }
        });


    }
}
