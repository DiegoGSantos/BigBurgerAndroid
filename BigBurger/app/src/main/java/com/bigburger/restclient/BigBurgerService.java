package com.bigburger.restclient;

import com.bigburger.model.Ingredient;
import com.bigburger.model.Order;
import com.bigburger.model.Sandwich;
import com.bigburger.restclient.parameter.CustomOrderParameter;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Query;
import retrofit.http.Url;

/**
 * Created by diegosantos on 7/29/17.
 */

public interface BigBurgerService {
//    @GET("lanche")
//    Observable<List<Sandwich>> list();

    @GET("lanche")
    Call<List<Sandwich>> list();

    @GET("ingrediente")
    Call<List<Ingredient>> listIngredients();

    @GET("pedido")
    Call<List<Order>> listOrders();

    @PUT
    Call<Sandwich> orderSandwich(@Url String url);

    @PUT
    Call<Sandwich> orderCustomSandwich(@Url String url, @Body CustomOrderParameter body);
}
