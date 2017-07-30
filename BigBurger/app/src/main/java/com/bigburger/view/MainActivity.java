package com.bigburger.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.bigburger.R;
import com.bigburger.adapter.SandwichAdapter;
import com.bigburger.databinding.ActivityMainBinding;
import com.bigburger.model.Sandwich;
import com.bigburger.viewmodel.SandwichesViewModel;

import java.util.List;

import static com.pharebee.util.UtilKt.isObjectNotNull;

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
    public void onDataRequestSuccess(List<Sandwich> sandwiches) {
        SandwichAdapter adapter = new SandwichAdapter();
        adapter.setSandwichesList(sandwiches);
        binding.mSandwichList.setAdapter(adapter);
        binding.mSandwichList.setLayoutManager(new LinearLayoutManager(this));
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
