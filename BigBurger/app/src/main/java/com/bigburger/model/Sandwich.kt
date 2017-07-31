package com.bigburger.model

import com.bigburger.util.isStringValid
import java.text.NumberFormat
import java.util.*

/**
 * Created by diegosantos on 7/28/17.
 */

fun getSandwichPrice(ingredients: List<Ingredient>): String {

    var price: Double? = 0.0

    for (ingredient in ingredients) {
        price = price?.plus(ingredient.price)
    }

    val brLocale = Locale("pt", "BR")
    val `in` = NumberFormat.getCurrencyInstance(brLocale)

    return `in`.format(price)
}

fun getSandwichDescription(ingredients: List<Ingredient>): String {
    var description = ""

    for (ingredient in ingredients) {

        if (isStringValid(description)) {
            description += ", "
        }

        description += ingredient.name
    }

    return description
}

class Sandwich(val id: Int, var name: String, var description: String?, var price: String?, val ingredients: List<Int>, val image: String)