package com.bigburger.viewmodel;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.bigburger.model.Ingredient;
import com.bigburger.model.MySandwich;
import com.bigburger.model.Sandwich;
import com.bigburger.view.SandwichDetailActivity;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by diegosantos on 7/28/17.
 */

public class ItemIngredientViewModel extends BaseObservable {

    Context mContext;
    Ingredient mIngredient;

    public ItemIngredientViewModel(Activity mContext, Ingredient ingredient) {
        this.mContext = mContext;
        this.mIngredient = ingredient;
    }

    @BindingAdapter("bind:imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setIngredient(Ingredient ingredient) {
        this.mIngredient = ingredient;
        notifyChange();
    }

    public String getImage(){
        return mIngredient.getImage();
    }

    public String getName(){
        return mIngredient.getName();
    }

    public String getPrice(){

        final Locale brLocale = new Locale("pt", "BR");
        NumberFormat in= NumberFormat.getCurrencyInstance(brLocale);

        return in.format(mIngredient.getPrice());
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
