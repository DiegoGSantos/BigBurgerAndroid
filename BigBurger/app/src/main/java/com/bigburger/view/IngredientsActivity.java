package com.bigburger.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigburger.R;
import com.bigburger.adapter.IngredientsAdapter;
import com.bigburger.adapter.SandwichAdapter;
import com.bigburger.databinding.ActivityIngredientsBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.model.Sandwich;
import com.bigburger.viewmodel.IngredientsViewModel;
import com.bigburger.viewmodel.SandwichesViewModel;

import java.util.List;

import static com.bigburger.util.UtilKt.isObjectNotNull;

public class IngredientsActivity extends AppCompatActivity implements IngredientsViewModel.DataListener {

    IngredientsViewModel viewModel;
    ActivityIngredientsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingredients);

        viewModel = new IngredientsViewModel(this);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolBar);
        if (isObjectNotNull(getSupportActionBar())){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        viewModel.listIngredients();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IngredientsActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onDataRequestSuccess(List<Ingredient> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
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
}
