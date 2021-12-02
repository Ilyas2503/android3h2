package com.example.android3hw2.network;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(provideInterceptor())
                .build();

    }

    private Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("https://android-3-mocker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create()).client(provideClient()).build();

   public MockerApi provideApi(){
       return retrofit.create(MockerApi.class);
   }
}
