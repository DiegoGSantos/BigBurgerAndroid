package com.bigburger.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigburger.R;
import com.bigburger.adapter.IngredientsAdapter;
import com.bigburger.adapter.OrdersAdapter;
import com.bigburger.databinding.ActivityOrdersBinding;
import com.bigburger.model.MyOrder;
import com.bigburger.model.Order;
import com.bigburger.viewmodel.OrdersViewModel;

import java.util.List;

public class OrdersActivity extends AppCompatActivity implements OrdersViewModel.DataListener {

    ActivityOrdersBinding binding;
    OrdersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_orders);
        viewModel = new OrdersViewModel(this);

        binding.setViewModel(viewModel);
        viewModel.listOrders();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, OrdersActivity.class);

        context.startActivity(starter);
    }

    @Override
    public void onDataRequestSuccess(List<MyOrder> myOrders) {
        OrdersAdapter adapter = new OrdersAdapter(myOrders);
        binding.mOrderList.setAdapter(adapter);
    }

    @Override
    public void onDataRequestFailure() {

    }

    @Override
    protected void onDestroy() {

        viewModel.onViewDestroy();

        super.onDestroy();
    }
}
