package com.bigburger.restclient.parameter;

import java.util.List;

/**
 * Created by diegosantos on 7/30/17.
 */

public class CustomOrderParameter {
    List<Integer> extras;

    public CustomOrderParameter(List<Integer> extras) {
        this.extras = extras;
    }
}
