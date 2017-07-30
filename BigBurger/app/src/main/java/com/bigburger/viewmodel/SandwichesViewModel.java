package com.bigburger.viewmodel;

import android.databinding.ObservableInt;
import android.view.View;

import com.bigburger.model.Ingredient;
import com.bigburger.model.Sandwich;
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

public class SandwichesViewModel implements ViewModel {

    public ObservableInt sandwichesListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt noSandwichesFoundListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt loadingVisibility = new ObservableInt(View.VISIBLE);
    DataListener mListener;
    Call<List<Sandwich>> callSandwiches;
    Call<List<Ingredient>> callIngredients;
    BigBurgerService service;

    List<Sandwich> sandwiches = new ArrayList<>();

    public SandwichesViewModel(DataListener mListener) {
        this.mListener = mListener;
        service = API.get().getUserService();
    }

    public void listSandwiches(){

        callSandwiches = service.list();
        callSandwiches.enqueue(new Callback<List<Sandwich>>() {
            @Override
            public void onResponse(final Response<List<Sandwich>> response) {
                sandwiches = response.body();
                listIngredients();
            }

            @Override
            public void onFailure(Throwable t) {
                sandwichesListVisibility.set(View.INVISIBLE);
                noSandwichesFoundListVisibility.set(View.VISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestFailure();
            }
        });
    }

    void listIngredients(){
        callIngredients = service.listIngredients();
        callIngredients.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Response<List<Ingredient>> response) {
                sandwichesListVisibility.set(View.VISIBLE);
                noSandwichesFoundListVisibility.set(View.INVISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestSuccess(sandwiches, response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                sandwichesListVisibility.set(View.INVISIBLE);
                noSandwichesFoundListVisibility.set(View.VISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestFailure();
            }
        });
    }

    @Override
    public void onViewDestroy() {

        if (isObjectNotNull(callSandwiches)){
            callSandwiches.cancel();
        }

        if (isObjectNotNull(callIngredients)){
            callIngredients.cancel();
        }
    }


    public interface DataListener{
        void onDataRequestSuccess(List<Sandwich> sandwiches, List<Ingredient> ingredients);
        void onDataRequestFailure();
    }
}
