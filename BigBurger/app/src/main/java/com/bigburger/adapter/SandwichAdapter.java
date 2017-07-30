package com.bigburger.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigburger.R;
import com.bigburger.databinding.AdapterSandwichBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.model.Sandwich;
import com.bigburger.view.SandwichDetailActivity;
import com.bigburger.viewmodel.ItemSandwichViewModel;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.bigburger.util.UtilKt.isStringValid;

/**
 * Created by diegosantos on 7/28/17.
 */

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichAdapterViewHolder> {

    List<Sandwich> mSandwiches = new ArrayList<>();
    List<Ingredient> mIngredients = new ArrayList<>();

    public SandwichAdapter(List<Sandwich> sandwiches, List<Ingredient> ingredients) {
        this.mSandwiches = sandwiches;
        this.mIngredients = ingredients;
    }

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

        Sandwich sandwich = mSandwiches.get(position);

        ArrayList<Ingredient> ingredients = getSandwichIngredients(sandwich.getIngredients());

        sandwich.setPrice(getSandwichPrice(ingredients));
        sandwich.setDescription(getSandwichDescription(ingredients));

        holder.bindSandwich(sandwich, ingredients);
    }

    private String getSandwichDescription(ArrayList<Ingredient> ingredients) {
        String description = "";

        for (Ingredient ingredient : ingredients){

            if (isStringValid(description)){
                description += ", ";
            }

            description += ingredient.getName();
        }

        return description;
    }

    private ArrayList<Ingredient> getSandwichIngredients(List<Integer> sandwichIngredientsId) {

        ArrayList<Ingredient> sandwichIngredients = new ArrayList<>();

        for (Ingredient ingredient : mIngredients){
            if (sandwichIngredientsId.contains(ingredient.getId())){
                sandwichIngredients.add(ingredient);
            }
        }

        return sandwichIngredients;
    }

    private String getSandwichPrice(List<Ingredient> ingredients) {

        Double price = 0.0;

        for (Ingredient ingredient : ingredients){
            price += ingredient.getPrice();
        }

        final Locale brLocale = new Locale("pt", "BR");
        NumberFormat in= NumberFormat.getCurrencyInstance(brLocale);

        return in.format(price);
    }

    @Override
    public int getItemCount() {
        return mSandwiches.size();
    }

    public static class SandwichAdapterViewHolder extends RecyclerView.ViewHolder {
        AdapterSandwichBinding mAdapterSandwichBinding;

        public SandwichAdapterViewHolder(AdapterSandwichBinding itemSandwichBinding) {
            super(itemSandwichBinding.clMainLayout);
            this.mAdapterSandwichBinding = itemSandwichBinding;
        }

        void bindSandwich(final Sandwich sandwich, final ArrayList<Ingredient> ingredients) {
            if (mAdapterSandwichBinding.getSandwich() == null) {
                mAdapterSandwichBinding.setSandwich(
                        new ItemSandwichViewModel((Activity) itemView.getContext(), sandwich, ingredients));
            } else {
                mAdapterSandwichBinding.getSandwich().setSandwich(sandwich);
            }

            Glide.with(itemView.getContext())
                    .load(sandwich.getImage())
                    .asBitmap()
                    .into(mAdapterSandwichBinding.mSandwichImage);
        }
    }

}
