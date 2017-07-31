package com.bigburger.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bigburger.R;
import com.bigburger.adapter.IngredientsAdapter;
import com.bigburger.databinding.ActivitySandwichDetailBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.model.MySandwich;
import com.bigburger.restclient.parameter.CustomOrderIngredients;
import com.bigburger.restclient.parameter.CustomOrderParameter;
import com.bigburger.viewmodel.ItemSandwichViewModel;
import com.bigburger.viewmodel.SandwicheDetailViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.bigburger.model.SandwichKt.getSandwichDescription;
import static com.bigburger.model.SandwichKt.getSandwichPrice;
import static com.bigburger.util.ConvertObjectUtils.getCustomOrderFromJson;
import static com.bigburger.util.ConvertObjectUtils.getCustomOrderIngredientsFromJson;
import static com.bigburger.util.ConvertObjectUtils.getMySandwichFromJson;
import static com.bigburger.util.UtilKt.getStringFromObject;
import static com.bigburger.util.UtilKt.isObjectNotNull;
import static com.bigburger.view.IngredientsActivity.EXTRA_INGREDIENTS;

public class SandwichDetailActivity extends AppCompatActivity {


    private static final String MY_SANDWICH_EXTRA = "sandwich_extra";

    ActivitySandwichDetailBinding binding;
    MySandwich mSandwich;
    SandwicheDetailViewModel sandwicheDetailViewModel;
    CustomOrderParameter customOrderParameter;
    ItemSandwichViewModel itemSandwichViewModel;
    IngredientsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sandwich_detail);

        mSandwich = getMySandwichFromJson(getIntent().getStringExtra(MY_SANDWICH_EXTRA));

        itemSandwichViewModel = new ItemSandwichViewModel(this, mSandwich.getSandwich(), mSandwich.getIngredients());

        binding.mSandwich.setSandwich(itemSandwichViewModel);

        adapter = new IngredientsAdapter(mSandwich.getIngredients(), false);
        binding.mIngredientList.setAdapter(adapter);

        binding.toolbarTitle.setText(mSandwich.getSandwich().getName());

        setSupportActionBar(binding.toolBar);
        if (isObjectNotNull(getSupportActionBar())){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sandwicheDetailViewModel = new SandwicheDetailViewModel(this, mSandwich.getSandwich());
        binding.setSandwichDetail(sandwicheDetailViewModel);

        setListeners();
    }

    private void setListeners() {
        binding.mAddToCart.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandwicheDetailViewModel.addToCart(v, customOrderParameter);
            }
        });

        binding.mAddIngredients.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sandwicheDetailViewModel.addExtraIngredients(v);
            }
        });
    }

    public static Intent getIntent(Context context, MySandwich mySandwich) {
        Intent starter = new Intent(context, SandwichDetailActivity.class);
        starter.putExtra(MY_SANDWICH_EXTRA, getStringFromObject(mySandwich));
        return starter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        sandwicheDetailViewModel.onViewDestroy();

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (isObjectNotNull(data) && data.hasExtra(EXTRA_INGREDIENTS)){
                String customOrderParameterJson = data.getStringExtra(EXTRA_INGREDIENTS);
                CustomOrderIngredients customOrderIngredients = getCustomOrderIngredientsFromJson(customOrderParameterJson);

                List<Ingredient> extraIngredients = mSandwich.getExtraIngredients();
                extraIngredients.addAll(customOrderIngredients.getExtras());

                mSandwich.setExtraIngredients(extraIngredients);

                List<Ingredient> ingredients = new ArrayList<>();
                ingredients.addAll(mSandwich.getIngredients());
                ingredients.addAll(mSandwich.getExtraIngredients());

                mSandwich.getSandwich().setPrice(getSandwichPrice(ingredients));
                mSandwich.getSandwich().setDescription(getSandwichDescription(ingredients));

                mSandwich.getSandwich().setName(mSandwich.getSandwich().getName().replace(" - Do seu jeito", "") + " - Do seu jeito");

                itemSandwichViewModel.setSandwich(mSandwich.getSandwich());
                binding.mSandwich.setSandwich(itemSandwichViewModel);

                adapter.setIngredientes(ingredients, false);
                binding.mIngredientList.setAdapter(adapter);

                List<Integer> extras = new ArrayList<Integer>();

                for (Ingredient ingredient : mSandwich.getExtraIngredients()){
                    extras.add(ingredient.getId());
                }

                customOrderParameter = new CustomOrderParameter(extras);
            }
        }
    }
}
