package com.bigburger.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigburger.R;
import com.bigburger.databinding.AdapterSandwichBinding;
import com.bigburger.model.Sandwich;
import com.bigburger.viewmodel.ItemSandwichViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegosantos on 7/28/17.
 */

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichAdapterViewHolder> {

    List<Sandwich> mSandwiches = new ArrayList<>();

    @Override
    public SandwichAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterSandwichBinding adapterSandwichBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_sandwich,
                        parent, false);

        return new SandwichAdapterViewHolder(adapterSandwichBinding);
    }

    @Override
    public void onBindViewHolder(SandwichAdapterViewHolder holder, int position) {
        holder.bindSandwich(mSandwiches.get(position));
    }

    @Override
    public int getItemCount() {
        return mSandwiches.size();
    }

    public void setSandwichesList(List<Sandwich> sandwichesList) {
        this.mSandwiches = sandwichesList;
        notifyDataSetChanged();
    }

    public static class SandwichAdapterViewHolder extends RecyclerView.ViewHolder {
        AdapterSandwichBinding mAdapterSandwichBinding;

        public SandwichAdapterViewHolder(AdapterSandwichBinding itemSandwichBinding) {
            super(itemSandwichBinding.clMainLayout);
            this.mAdapterSandwichBinding = itemSandwichBinding;
        }

        void bindSandwich(Sandwich sandwich) {
            if (mAdapterSandwichBinding.getSandwich() == null) {
                mAdapterSandwichBinding.setSandwich(
                        new ItemSandwichViewModel(itemView.getContext(), sandwich));
            } else {
                mAdapterSandwichBinding.getSandwich().setSandwich(sandwich);
            }

            Glide.with(itemView.getContext())
                    .load(sandwich.getImage())
                    .asBitmap()
                    .into(mAdapterSandwichBinding.mMallImage);
        }
    }

}
