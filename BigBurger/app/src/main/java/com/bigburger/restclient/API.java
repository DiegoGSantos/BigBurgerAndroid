package com.bigburger.restclient;

import android.provider.SyncStateContract;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by diegosantos on 5/30/17.
 */

public class API {

//    For server test use this repo: https://github.com/typicode/json-server

    public static final String ENDPOINT_URL = "http://192.168.1.32:8080/api/";
    private static API instance;

    private API() {
        /* IGNORED */
    }

    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    public BigBurgerService getUserService() {

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(BigBurgerService.class);
    }

}