package com.pixel.pizzayah.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PizzaData(
    val id: Int,
    @DrawableRes
    val breadImage: Int,
    val ingredients: List<Ingredient> = emptyList(),
)

