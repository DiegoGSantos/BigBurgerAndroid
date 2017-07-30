package com.bigburger.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigburger.R;
import com.bigburger.databinding.AdapterIngredientBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.viewmodel.ItemIngredientViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegosantos on 7/28/17.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientAdapterViewHolder> {

    List<Ingredient> mIngredientes = new ArrayList<>();

    public IngredientsAdapter(List<Ingredient> ingredientes) {
        this.mIngredientes = ingredientes;
    }

    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterIngredientBinding adapterIngredientBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_ingredient,
                        parent, false);

        return new IngredientAdapterViewHolder(adapterIngredientBinding);
    }

    @Override
    public void onBindViewHolder(IngredientAdapterViewHolder holder, int position) {

        Ingredient ingredient = mIngredientes.get(position);

        holder.bindIngredient(ingredient);
    }

    @Override
    public int getItemCount() {
        return mIngredientes.size();
    }

    public static class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        AdapterIngredientBinding mAdapterIngredientBinding;

        public IngredientAdapterViewHolder(AdapterIngredientBinding itemIngredientBinding) {
            super(itemIngredientBinding.clMainLayout);
            this.mAdapterIngredientBinding = itemIngredientBinding;
        }

        void bindIngredient(final Ingredient ingredient) {
            if (mAdapterIngredientBinding.getIngredient() == null) {
                mAdapterIngredientBinding.setIngredient(
                        new ItemIngredientViewModel((Activity) itemView.getContext(), ingredient));
            } else {
                mAdapterIngredientBinding.getIngredient().setIngredient(ingredient);
            }

            Glide.with(itemView.getContext())
                    .load(ingredient.getImage())
                    .asBitmap()
                    .into(mAdapterIngredientBinding.mIngredientImage);
        }
    }

}
