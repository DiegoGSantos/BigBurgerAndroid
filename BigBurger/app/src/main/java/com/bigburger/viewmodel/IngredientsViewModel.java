package com.bigburger.viewmodel;

import android.databinding.ObservableInt;
import android.view.View;

import com.bigburger.model.Ingredient;
import com.bigburger.model.Ingredient;
import com.bigburger.restclient.API;
import com.bigburger.restclient.BigBurgerService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static com.bigburger.util.UtilKt.isObjectNotNull;

/**
 * Created by diegosantos on 7/30/17.
 */

public class IngredientsViewModel implements ViewModel{

    public ObservableInt ingredientsListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt noIngredientsFoundListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt loadingVisibility = new ObservableInt(View.VISIBLE);
    DataListener mListener;
    Call<List<Ingredient>> callIngredientes;
    BigBurgerService service;

    public IngredientsViewModel(DataListener mListener) {
        this.mListener = mListener;
        service = API.get().getUserService();
    }

    public void listIngredients(){
        callIngredientes = service.listIngredients();
        callIngredientes.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Response<List<Ingredient>> response) {
                ingredientsListVisibility.set(View.VISIBLE);
                noIngredientsFoundListVisibility.set(View.INVISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                ingredientsListVisibility.set(View.INVISIBLE);
                noIngredientsFoundListVisibility.set(View.VISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestFailure();
            }
        });
    }

    @Override
    public void onViewDestroy() {
        if (isObjectNotNull(callIngredientes)){
            callIngredientes.cancel();
        }
    }

    public interface DataListener{
        void onDataRequestSuccess(List<Ingredient> ingredients);
        void onDataRequestFailure();
    }
}
