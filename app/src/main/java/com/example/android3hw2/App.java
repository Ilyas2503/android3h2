package com.example.android3hw2;

import android.app.Application;

import com.example.android3hw2.network.MockerModel;
import com.example.android3hw2.network.RetrofitClient;
import com.example.android3hw2.network.MockerApi;

import retrofit2.Retrofit;

public class App extends Application {
    public static RetrofitClient retrofitClient;
    public static MockerApi mockerApi;

    @Override
    public void onCreate() {
        super.onCreate();
    retrofitClient = new RetrofitClient();
    mockerApi = retrofitClient.provideApi();
    }
}
