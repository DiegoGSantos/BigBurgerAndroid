package com.bigburger.restclient;

import com.bigburger.model.Sandwich;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by diegosantos on 7/29/17.
 */

public interface BigBurgerService {
//    @GET("lanche")
//    Observable<List<Sandwich>> list();

    @GET("lanche")
    Call<List<Sandwich>> list();
}
