package com.example.intermedio.utils;

import android.icu.text.StringSearch;

import com.example.intermedio.models.CallResult;
import com.example.intermedio.models.Empleado;
import com.example.intermedio.models.LoginResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReporteService {

    // http://isantosp.com/cursoAndroid/getEmpleado.php?id=5
    @GET("getEmpleado.php")
    Call<Empleado> getEmpleadoUnico(@Query("id") int idEmpleado);

    @FormUrlEncoded
    @POST("addReporte.php")
    Call<CallResult> agregarReporte (@Field("nombre") String nombre,
                                     @Field("email") String email,
                                     @Field("telefono") String telefono,
                                     @Field("reporte") String reporte);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResult> login (@Field("username") String usuario,
                             @Field("password") String contrasena);


    // http://isantosp.com/cursoAndroid/getAll.php
    //@GET("getall.php")
    //Call<List<Empleado>> getTodos();

}
