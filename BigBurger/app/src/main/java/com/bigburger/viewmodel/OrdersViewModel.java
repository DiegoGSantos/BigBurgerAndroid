package com.bigburger.viewmodel;

import android.databinding.ObservableInt;
import android.view.View;

import com.bigburger.model.MyOrder;
import com.bigburger.model.MySandwich;
import com.bigburger.model.Order;
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

public class OrdersViewModel implements ViewModel{

    public ObservableInt ordersListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt noOrdersFoundListVisibility = new ObservableInt(View.INVISIBLE);
    public ObservableInt loadingVisibility = new ObservableInt(View.VISIBLE);
    DataListener mListener;
    Call<List<Order>> callOrders;
    Call<List<Sandwich>> callSandwiches;
    BigBurgerService service;

    List<Sandwich> sandwiches;

    public OrdersViewModel(DataListener mListener) {
        this.mListener = mListener;
        service = API.get().getUserService();
    }

    public void getOrders(){
        callOrders = service.listOrders();
        callOrders.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Response<List<Order>> response) {
                ordersListVisibility.set(View.VISIBLE);
                noOrdersFoundListVisibility.set(View.INVISIBLE);
                loadingVisibility.set(View.INVISIBLE);

                List<Order> orders = response.body();
                List<MyOrder> myOrders = new ArrayList<MyOrder>();

                for (Order order : orders){

                    Sandwich sandwich = getSandwichFromId(order.getId_sandwich());

                    myOrders.add(new MyOrder(order.getId(), sandwich, order.getDate()));
                }

                mListener.onDataRequestSuccess(myOrders);
            }

            @Override
            public void onFailure(Throwable t) {
                ordersListVisibility.set(View.INVISIBLE);
                noOrdersFoundListVisibility.set(View.VISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestFailure();
            }
        });
    }

    private Sandwich getSandwichFromId(int id) {

        for (Sandwich sandwich : sandwiches){
            if (sandwich.getId() == id) return sandwich;
        }

        return null;
    }

    public void listOrders(){
        listSandwiches();
    }

    void listSandwiches(){
        callSandwiches = service.list();
        callSandwiches.enqueue(new Callback<List<Sandwich>>() {
            @Override
            public void onResponse(Response<List<Sandwich>> response) {
                sandwiches = response.body();
                getOrders();
            }

            @Override
            public void onFailure(Throwable t) {
                ordersListVisibility.set(View.INVISIBLE);
                noOrdersFoundListVisibility.set(View.VISIBLE);
                loadingVisibility.set(View.INVISIBLE);
                mListener.onDataRequestFailure();
            }
        });
    }

    @Override
    public void onViewDestroy() {
        if (isObjectNotNull(callOrders)){
            callOrders.cancel();
        }

        if (isObjectNotNull(callSandwiches)){
            callSandwiches.cancel();
        }
    }

    public interface DataListener{
        void onDataRequestSuccess(List<MyOrder> myOrders);
        void onDataRequestFailure();
    }
}
