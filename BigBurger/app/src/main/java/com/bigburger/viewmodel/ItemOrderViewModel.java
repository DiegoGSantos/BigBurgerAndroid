package com.bigburger.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bigburger.model.MyOrder;
import com.bigburger.util.DateUtil;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by diegosantos on 7/28/17.
 */

public class ItemOrderViewModel extends BaseObservable {

    Context mContext;
    MyOrder mMyOrder;

    public ItemOrderViewModel(Activity mContext, MyOrder myOrder) {
        this.mContext = mContext;
        this.mMyOrder = myOrder;
    }

    public void setMyOrder(MyOrder myOrder) {
        this.mMyOrder = myOrder;
        notifyChange();
    }

    public String getImage(){
        return mMyOrder.getSandwich().getImage();
    }

    public String getName(){
        return mMyOrder.getSandwich().getName();
    }

    public String getDate(){

        Date orderDate = DateUtil.timestampToDate(mMyOrder.getOrderDate());
        String orderDateFormated = DateUtil.getDayMonthYearTime(orderDate);

        return orderDateFormated;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}
