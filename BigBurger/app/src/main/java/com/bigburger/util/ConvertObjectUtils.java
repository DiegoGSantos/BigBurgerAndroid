package com.bigburger.util;

import com.bigburger.model.MySandwich;
import com.bigburger.restclient.parameter.CustomOrderIngredients;
import com.bigburger.restclient.parameter.CustomOrderParameter;
import com.google.gson.Gson;

import static com.bigburger.util.UtilKt.isStringValid;

/**
 * Created by diegosantos on 7/30/17.
 */

public class ConvertObjectUtils {
    public static MySandwich getMySandwichFromJson(String sandwichJson){
        MySandwich mySandwich = null;

        if(isStringValid(sandwichJson)) {
            Gson mallGson = new Gson();

            mySandwich = mallGson.fromJson(sandwichJson, MySandwich.class);
        }

        return mySandwich;
    }

    public static CustomOrderParameter getCustomOrderFromJson(String customOrderJson){
        CustomOrderParameter customOrder = null;

        if(isStringValid(customOrderJson)) {
            Gson mallGson = new Gson();

            customOrder = mallGson.fromJson(customOrderJson, CustomOrderParameter.class);
        }

        return customOrder;
    }

    public static CustomOrderIngredients getCustomOrderIngredientsFromJson(String customOrderJson){
        CustomOrderIngredients customOrder = null;

        if(isStringValid(customOrderJson)) {
            Gson mallGson = new Gson();

            customOrder = mallGson.fromJson(customOrderJson, CustomOrderIngredients.class);
        }

        return customOrder;
    }
}
