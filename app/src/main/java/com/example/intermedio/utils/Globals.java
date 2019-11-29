package com.example.intermedio.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globals {

    public static final String BASE_API_URL = "http://isantosp.com/cursoAndroid/";

    private static Retrofit retrofit = null;
    public static Retrofit getApi(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
        return retrofit;
    }


}
