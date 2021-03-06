package com.bigburger.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigburger.R;
import com.bigburger.databinding.AdapterSandwichBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.model.Sandwich;
import com.bigburger.viewmodel.ItemSandwichViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.bigburger.model.SandwichKt.getSandwichDescription;
import static com.bigburger.model.SandwichKt.getSandwichPrice;
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

    private ArrayList<Ingredient> getSandwichIngredients(List<Integer> sandwichIngredientsId) {

        ArrayList<Ingredient> sandwichIngredients = new ArrayList<>();

        for (Ingredient ingredient : mIngredients){
            if (sandwichIngredientsId.contains(ingredient.getId())){
                sandwichIngredients.add(ingredient);
            }
        }

        return sandwichIngredients;
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
        }
    }

}
