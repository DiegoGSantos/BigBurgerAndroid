package com.bigburger.util;

import com.bigburger.model.MySandwich;
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
}
