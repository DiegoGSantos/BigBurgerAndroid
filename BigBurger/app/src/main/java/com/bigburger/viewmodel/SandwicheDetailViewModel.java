package com.bigburger.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import com.bigburger.model.Sandwich;
import com.bigburger.restclient.API;
import com.bigburger.view.IngredientsActivity;
import com.bigburger.view.OrdersActivity;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by diegosantos on 7/30/17.
 */

public class SandwicheDetailViewModel extends BaseObservable implements ViewModel {

    Context mContext;
    Sandwich mSandwich;
    Call<Sandwich> addToCart;

    public SandwicheDetailViewModel(Context mContext, Sandwich sandwich) {
        this.mContext = mContext;
        this.mSandwich = sandwich;
    }

    public void addToCart(View view){
        addToCart = API.get().getUserService().addLanchToCart("pedido/" + mSandwich.getId());

        addToCart.enqueue(new Callback<Sandwich>() {
            @Override
            public void onResponse(Response<Sandwich> response) {
                OrdersActivity.start(mContext);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void addExtraIngredients(View view){
        IngredientsActivity.start(mContext);
    }

    @Override
    public void onViewDestroy() {
        addToCart.cancel();
    }
}
