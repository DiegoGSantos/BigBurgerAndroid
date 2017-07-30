package com.bigburger.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.bigburger.R;
import com.bigburger.adapter.SandwichAdapter;
import com.bigburger.databinding.ActivityMainBinding;
import com.bigburger.model.Ingredient;
import com.bigburger.model.Sandwich;
import com.bigburger.viewmodel.SandwichesViewModel;

import java.util.List;

import static com.bigburger.util.UtilKt.isObjectNotNull;

public class MainActivity extends AppCompatActivity implements SandwichesViewModel.DataListener {

    ActivityMainBinding binding;
    SandwichesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new SandwichesViewModel(this);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolBar);
        if (isObjectNotNull(getSupportActionBar())){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        viewModel.listSandwiches();
    }

    @Override
    public void onDataRequestSuccess(List<Sandwich> sandwiches, List<Ingredient> ingredients) {
        SandwichAdapter adapter = new SandwichAdapter(sandwiches, ingredients);
        binding.mSandwichList.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
}
