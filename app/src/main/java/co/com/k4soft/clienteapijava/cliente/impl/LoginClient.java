package co.com.k4soft.clienteapijava.cliente.impl;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface LoginClient {

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<ResponseBody> login(@Header("Authorization") String credentials, @Field("username") String username, @Field("password") String password, @Field("grant_type") String grantType );

}
