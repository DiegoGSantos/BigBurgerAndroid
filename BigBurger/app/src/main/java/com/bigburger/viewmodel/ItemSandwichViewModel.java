package com.bigburger.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bigburger.model.Sandwich;
import com.bigburger.restclient.API;
import com.bigburger.restclient.BigBurgerService;
import com.bumptech.glide.Glide;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by diegosantos on 7/28/17.
 */

public class ItemSandwichViewModel extends BaseObservable {

    Context mContext;
    Sandwich mSandwich;

    public ItemSandwichViewModel(Context mContext, Sandwich mSandwich) {
        this.mContext = mContext;
        this.mSandwich = mSandwich;
    }

    @BindingAdapter("bind:imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
//        context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
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
}
