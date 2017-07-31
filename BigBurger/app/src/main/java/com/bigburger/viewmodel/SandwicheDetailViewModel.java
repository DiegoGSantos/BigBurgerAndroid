package com.bigburger.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.bigburger.model.Sandwich;
import com.bigburger.restclient.API;
import com.bigburger.restclient.parameter.CustomOrderParameter;
import com.bigburger.view.IngredientsActivity;
import com.bigburger.view.OrdersActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static com.bigburger.util.UtilKt.isObjectNotNull;

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

    public void addToCart(View view, CustomOrderParameter customOrderParameter){

        if (isObjectNotNull(customOrderParameter)){

            addToCart = API.get().getUserService().orderCustomSandwich("pedido/" + mSandwich.getId(), customOrderParameter);

        }else{

            addToCart = API.get().getUserService().orderSandwich("pedido/" + mSandwich.getId());

        }

        addToCart.enqueue(new Callback<Sandwich>() {
            @Override
            public void onResponse(Response<Sandwich> response) {
                OrdersActivity.start(mContext);

                if (mContext instanceof Activity){
                    ((Activity) mContext).finish();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void addExtraIngredients(View view){
        ((Activity) mContext).startActivityForResult(IngredientsActivity.getIntent(mContext), 101);
    }

    @Override
    public void onViewDestroy() {
        if (isObjectNotNull(addToCart)) addToCart.cancel();
    }
}
