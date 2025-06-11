package com.pixel.pizzayah.presentation.providers

import com.pixel.pizzayah.R
import com.pixel.pizzayah.presentation.model.PizzaData

class PizzaDataProvider {
    val pizzaList = listOf(
        PizzaData(
            id = 1,
            breadImage = R.drawable.bread_1,
            ingredients = IngredientsProvider().ingredients
        ),
        PizzaData(
            id = 2,
            breadImage = R.drawable.bread_2,
            ingredients = IngredientsProvider().ingredients
        ),
        PizzaData(
            id = 3,
            breadImage = R.drawable.bread_3,
            ingredients = IngredientsProvider().ingredients
        ),
        PizzaData(
            id = 4,
            breadImage = R.drawable.bread_4,
            ingredients = IngredientsProvider().ingredients
        ),
        PizzaData(
            id = 5,
            breadImage = R.drawable.bread_5,
            ingredients = IngredientsProvider().ingredients
        )
    )
}