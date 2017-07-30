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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegosantos on 7/28/17.
 */

public class ItemSandwichViewModel extends BaseObservable {

    Context mContext;
    Sandwich mSandwich;
    List<Ingredient> mIngredients;

    public ItemSandwichViewModel(Activity mContext, Sandwich mSandwich, List<Ingredient> ingredients) {
        this.mContext = mContext;
        this.mSandwich = mSandwich;
        mIngredients = ingredients;
    }

    @BindingAdapter("bind:imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {

        MySandwich mySandwich = new MySandwich(mSandwich, mIngredients, new ArrayList<Ingredient>());

        Intent intent = SandwichDetailActivity.getIntent(mContext, mySandwich);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                    (Activity) mContext, view, view.getTransitionName()).toBundle());
        }else{
            mContext.startActivity(intent);
        }
    }

    public void setSandwich(Sandwich sandwich) {
        this.mSandwich = sandwich;
        notifyChange();
    }

    public String getImage(){
        return mSandwich.getImage();
    }

    public String getName(){
        return mSandwich.getName();
    }

    public String getDescription(){
        return mSandwich.getDescription();
    }

    public String getPrice(){
        return mSandwich.getPrice();
    }

    public List<Ingredient> getIngredients(){
        return mIngredients;
    }
}
