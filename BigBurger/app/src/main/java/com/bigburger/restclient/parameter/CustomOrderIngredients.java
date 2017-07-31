package com.bigburger.restclient.parameter;

import com.bigburger.model.Ingredient;

import java.util.List;

/**
 * Created by diegosantos on 7/30/17.
 */

public class CustomOrderIngredients {
    List<Ingredient> extras;

    public CustomOrderIngredients(List<Ingredient> extras) {
        this.extras = extras;
    }

    public List<Ingredient> getExtras() {
        return extras;
    }
}
