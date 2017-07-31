package com.bigburger.model

import com.bigburger.util.isStringValid
import java.text.NumberFormat
import java.util.*

/**
 * Created by diegosantos on 7/28/17.
 */

fun getSandwichPrice(ingredients: List<Ingredient>): String {

    var hasLettuce: Boolean = false
    var hasBacon: Boolean = false
    var numberOfMeat: Int = 0
    var numberOfCheese: Int = 0
    var price: Double? = 0.0

    for (ingredient in ingredients) {

        if(ingredient.name == "Alface"){
            hasLettuce = true
        } else if (ingredient.name == "Bacon"){
            hasBacon = true
        } else if (ingredient.name == "Hamburguer de Carne"){
            numberOfMeat++
        } else if (ingredient.name == "Queijo"){
            numberOfCheese++
        }

        if (!(ingredient.name == "Hamburguer de Carne" && numberOfMeat > 1 && (numberOfMeat)?.rem(3) == 0) &&
            !(ingredient.name == "Queijo" && numberOfCheese > 1 && (numberOfCheese)?.rem(3) == 0)){
            price = price?.plus(ingredient.price)
        }else {
            var s: String = ""
        }

    }

    if (hasLettuce && !hasBacon){
        price = price?.times(0.9)
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