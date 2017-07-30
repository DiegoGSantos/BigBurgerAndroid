package com.bigburger.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigburger.R;
import com.bigburger.databinding.AdapterOrderBinding;
import com.bigburger.model.MyOrder;
import com.bigburger.util.DateUtil;
import com.bigburger.viewmodel.ItemOrderViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by diegosantos on 7/28/17.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyOrderAdapterViewHolder> {

    List<MyOrder> mMyOrders = new ArrayList<>();

    public OrdersAdapter(List<MyOrder> orders) {
        this.mMyOrders = orders;
    }

    @Override
    public MyOrderAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterOrderBinding adapterMyOrderBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_order,
                        parent, false);

        return new MyOrderAdapterViewHolder(adapterMyOrderBinding);
    }

    @Override
    public void onBindViewHolder(MyOrderAdapterViewHolder holder, int position) {

        MyOrder order = mMyOrders.get(position);

        holder.bindMyOrder(order);
    }

    @Override
    public int getItemCount() {
        return mMyOrders.size();
    }

    public static class MyOrderAdapterViewHolder extends RecyclerView.ViewHolder {
        AdapterOrderBinding mAdapterMyOrderBinding;

        public MyOrderAdapterViewHolder(AdapterOrderBinding itemMyOrderBinding) {
            super(itemMyOrderBinding.clMainLayout);
            this.mAdapterMyOrderBinding = itemMyOrderBinding;
        }

        void bindMyOrder(final MyOrder order) {
            if (mAdapterMyOrderBinding.getOrder() == null) {
                mAdapterMyOrderBinding.setOrder(
                        new ItemOrderViewModel((Activity) itemView.getContext(), order));
            } else {
                mAdapterMyOrderBinding.getOrder().setMyOrder(order);
            }
        }
    }

}
