package com.bigburger;

import com.bigburger.model.Ingredient;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.bigburger.model.SandwichKt.getSandwichPrice;
import static org.junit.Assert.assertEquals;

/**
 * Created by diegosantos on 7/30/17.
 */

public class CalculateSandwichPriceTest {
    @Test
    public void calculatedPrice_isCorrect() throws Exception {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1, "", 0.5, "", 0));
        ingredients.add(new Ingredient(1, "", 1.0, "", 0));
        ingredients.add(new Ingredient(1, "", 0.5, "", 0));
        ingredients.add(new Ingredient(1, "", 0.5, "", 0));

        assertEquals("R$ 2,50", getSandwichPrice(ingredients));
    }
}
