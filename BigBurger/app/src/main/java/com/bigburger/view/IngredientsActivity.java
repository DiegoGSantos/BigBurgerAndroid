package com.bigburger.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bigburger.R;
import com.bigburger.adapter.IngredientsAdapter;
import com.bigburger.databinding.ActivityIngredientsBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.restclient.parameter.CustomOrderIngredients;
import com.bigburger.restclient.parameter.CustomOrderParameter;
import com.bigburger.viewmodel.IngredientsViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.bigburger.util.UtilKt.getStringFromObject;
import static com.bigburger.util.UtilKt.isObjectNotNull;

public class IngredientsActivity extends AppCompatActivity implements IngredientsViewModel.DataListener {

    public static final String EXTRA_INGREDIENTS = "extra_ingredients";
    IngredientsViewModel viewModel;
    ActivityIngredientsBinding binding;
    IngredientsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingredients);

        setSupportActionBar(binding.toolBar);
        if (isObjectNotNull(getSupportActionBar())){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new IngredientsViewModel(this);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolBar);
        if (isObjectNotNull(getSupportActionBar())){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        viewModel.listIngredients();

        setListeners();
    }

    private void setListeners() {
        binding.mAddIngredients.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();

                List<Ingredient> extraIngredients = adapter.getChosenIngredients();

                data.putExtra(EXTRA_INGREDIENTS, getStringFromObject(new CustomOrderIngredients(extraIngredients)));
                setResult(RESULT_OK, data);

                finish();
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IngredientsActivity.class);
        context.startActivity(starter);
    }

    public static Intent getIntent(Context context){
        return new Intent(context, IngredientsActivity.class);
    }

    @Override
    public void onDataRequestSuccess(List<Ingredient> ingredients) {
        adapter = new IngredientsAdapter(ingredients, true);
        binding.mIngredientList.setAdapter(adapter);
    }

    @Override
    public void onDataRequestFailure() {

    }

    @Override
    protected void onDestroy() {

        viewModel.onViewDestroy();

        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
