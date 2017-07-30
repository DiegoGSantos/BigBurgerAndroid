package com.bigburger.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bigburger.R;
import com.bigburger.adapter.IngredientsAdapter;
import com.bigburger.databinding.ActivitySandwichDetailBinding;
import com.bigburger.model.MySandwich;
import com.bigburger.viewmodel.ItemSandwichViewModel;
import com.bumptech.glide.Glide;

import static com.bigburger.util.ConvertObjectUtils.getMySandwichFromJson;
import static com.bigburger.util.UtilKt.getStringFromObject;

public class SandwichDetailActivity extends AppCompatActivity {


    private static final String MY_SANDWICH_EXTRA = "sandwich_extra";

    ActivitySandwichDetailBinding binding;
    MySandwich mSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sandwich_detail);

        mSandwich = getMySandwichFromJson(getIntent().getStringExtra(MY_SANDWICH_EXTRA));

        ItemSandwichViewModel itemSandwichViewModel = new ItemSandwichViewModel(this, mSandwich.getSandwich(), mSandwich.getIngredients());

        binding.mSandwich.setSandwich(itemSandwichViewModel);

        binding.mIngredientList.setAdapter(new IngredientsAdapter(mSandwich.getIngredients()));

        binding.toolbarTitle.setText(mSandwich.getSandwich().getName());
    }

    public static Intent getIntent(Context context, MySandwich mySandwich) {
        Intent starter = new Intent(context, SandwichDetailActivity.class);
        starter.putExtra(MY_SANDWICH_EXTRA, getStringFromObject(mySandwich));
        return starter;
    }
}
