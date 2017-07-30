package com.bigburger.viewmodel;

import android.databinding.ObservableInt;
import android.os.Handler;
import android.view.View;

import com.bigburger.model.Sandwich;
import com.bigburger.restclient.API;
import com.bigburger.restclient.BigBurgerService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static com.pharebee.util.UtilKt.isObjectNotNull;

/**
 * Created by diegosantos on 7/30/17.
 */

public class SandwichesViewModel {

    public ObservableInt sandwichesListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt noSandwichesFoundListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt loadingVisibility = new ObservableInt(View.VISIBLE);
    DataListener mListener;
    Call<List<Sandwich>> call;

    public SandwichesViewModel(DataListener mListener) {
        this.mListener = mListener;
    }

    public void listSandwiches(){
        BigBurgerService service = API.get().getUserService();

        call = service.list();
        call.enqueue(new Callback<List<Sandwich>>() {
            @Override
            public void onResponse(final Response<List<Sandwich>> response) {
                sandwichesListVisibility.set(View.VISIBLE);
                noSandwichesFoundListVisibility.set(View.INVISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestSuccess(response.body());
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

    public void onViewDestroy(){
        if (isObjectNotNull(call)){
            call.cancel();
        }
    }

    public interface DataListener{
        void onDataRequestSuccess(List<Sandwich> sandwiches);
        void onDataRequestFailure();
    }
}
