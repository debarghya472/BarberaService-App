package com.barbera.barberaserviceapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RCI_otp {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://script.google.com/macros/s/AKfycbwSTObrfFItarK7hwpxakUIbc1cG2hvjVfZJXMmBw5GmFHJtH0w3lVJJWttrinlRUAY/";

    public static Retrofit getRetrofiInstance() {
        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
