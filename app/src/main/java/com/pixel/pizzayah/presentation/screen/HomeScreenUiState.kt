package com.pixel.pizzayah.presentation.screen

import com.pixel.pizzayah.presentation.model.PizzaData
import com.pixel.pizzayah.presentation.model.PizzaSize
import com.pixel.pizzayah.presentation.providers.PizzaDataProvider

data class HomeScreenUiState(
    val pizzaList: List<PizzaData> = emptyList(),
    val selectedSize: PizzaSize = PizzaSize.MEDIUM,
    val selectedPizza: PizzaData = PizzaDataProvider().pizzaList.first()
)