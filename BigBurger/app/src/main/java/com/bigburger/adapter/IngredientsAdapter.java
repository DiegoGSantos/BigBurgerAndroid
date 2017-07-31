package com.bigburger.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private static List<Ingredient> mIngredientes = new ArrayList<>();
    static boolean showQtde = false;

    public IngredientsAdapter(List<Ingredient> ingredientes, boolean showQtde) {
        this.mIngredientes = ingredientes;
        this.showQtde = showQtde;
    }

    public void setIngredientes(List<Ingredient> ingredientes, boolean showQtde){
        this.showQtde = showQtde;
        mIngredientes = ingredientes;
        notifyDataSetChanged();
    }

    public List<Ingredient> getChosenIngredients(){
        List<Ingredient> extraIngredients = new ArrayList<>();

        for (Ingredient ingredient : mIngredientes){
            if (ingredient.getQtde() > 0){
                for (int i = 0; i < ingredient.getQtde(); i++){
                    extraIngredients.add(ingredient);
                }
            }
        }

        return extraIngredients;
    }

    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final AdapterIngredientBinding adapterIngredientBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_ingredient,
                        parent, false);

        return new IngredientAdapterViewHolder(adapterIngredientBinding);
    }

    @Override
    public void onBindViewHolder(IngredientAdapterViewHolder holder, int position) {

        Ingredient ingredient = mIngredientes.get(position);

        holder.bindIngredient(ingredient, position);
    }

    @Override
    public int getItemCount() {
        return mIngredientes.size();
    }

    public static class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        AdapterIngredientBinding mAdapterIngredientBinding;
        int position = 0;

        public IngredientAdapterViewHolder(AdapterIngredientBinding itemIngredientBinding) {
            super(itemIngredientBinding.clMainLayout);
            this.mAdapterIngredientBinding = itemIngredientBinding;

            mAdapterIngredientBinding.decreaseQtde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(mAdapterIngredientBinding.mQtde.getText().toString());

                    if (i > 0){
                        i--;
                        mAdapterIngredientBinding.mQtde.setText(String.valueOf(i));
                        mIngredientes.get(position).setQtde(i);
                    }
                }
            });

            mAdapterIngredientBinding.increaseQtde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = Integer.parseInt(mAdapterIngredientBinding.mQtde.getText().toString());
                    i++;
                    mAdapterIngredientBinding.mQtde.setText(String.valueOf(i));

                    mIngredientes.get(position).setQtde(i);
                }
            });
        }

        void bindIngredient(final Ingredient ingredient, int position) {

            this.position = position;

            mAdapterIngredientBinding.llQtde.setVisibility(showQtde ? View.VISIBLE : View.GONE);

            if (mAdapterIngredientBinding.getIngredient() == null) {
                mAdapterIngredientBinding.setIngredient(
                        new ItemIngredientViewModel((Activity) itemView.getContext(), ingredient));
            } else {
                mAdapterIngredientBinding.getIngredient().setIngredient(ingredient);
            }
        }
    }

}
