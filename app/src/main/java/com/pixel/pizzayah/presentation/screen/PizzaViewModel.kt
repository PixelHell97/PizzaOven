package com.pixel.pizzayah.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixel.pizzayah.presentation.model.Ingredient
import com.pixel.pizzayah.presentation.model.PizzaSize
import com.pixel.pizzayah.presentation.providers.PizzaDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PizzaViewModel : ViewModel() {
    private val _pizzaDataState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState())
    val pizzaDataState = _pizzaDataState.asStateFlow()

    init {
        loadPizzaData()
    }

    private fun loadPizzaData() {
        viewModelScope.launch {
            _pizzaDataState.update { it.copy(pizzaList = PizzaDataProvider().pizzaList) }
        }
    }

    fun onPizzaSizeClicked(size: PizzaSize) {
        _pizzaDataState.update { it.copy(selectedSize = size) }
    }

    fun onIngredientClicked(ingredient: Ingredient) {
        _pizzaDataState.update { state ->
            val updatedPizzaList =
                state.pizzaList.map { pizza ->
                    if (pizza.id == state.selectedPizza.id) {
                        val maxOrder = pizza.ingredients.maxOfOrNull { it.selectionOrder } ?: -1

                        val updatedIngredients =
                            pizza.ingredients.map { item ->
                                if (item.id == ingredient.id) {
                                    if (!item.selected) {
                                        item.copy(selected = true, selectionOrder = maxOrder + 1)
                                    } else {
                                        item.copy(selected = false, selectionOrder = -1)
                                    }
                                } else {
                                    item
                                }
                            }

                        pizza.copy(ingredients = updatedIngredients)
                    } else {
                        pizza
                    }
                }

            val updatedPizza = updatedPizzaList.first { it.id == state.selectedPizza.id }

            state.copy(
                pizzaList = updatedPizzaList,
                selectedPizza = updatedPizza,
            )
        }
    }

    fun setSelectedPizza(breadIndex: Int) {
        _pizzaDataState.update { state ->
            val newSelectedPizza = state.pizzaList[breadIndex]
            state.copy(
                selectedPizza = newSelectedPizza,
            )
        }
    }
}
