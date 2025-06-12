package com.pixel.pizzayah.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixel.pizzayah.presentation.model.Ingredient
import com.pixel.pizzayah.presentation.model.PizzaData
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
            val updatedPizzaList = updatePizzaList(ingredient)
            val updatedSelectedPizza = updatedPizzaList.first { it.id == state.selectedPizza.id }

            state.copy(
                pizzaList = updatedPizzaList,
                selectedPizza = updatedSelectedPizza,
            )
        }
    }

    private fun updatePizzaList(ingredient: Ingredient): List<PizzaData> {
        val selectedPizzaId = _pizzaDataState.value.selectedPizza.id

        return _pizzaDataState.value.pizzaList.map { pizza ->
            if (pizza.id == selectedPizzaId) {
                updatePizza(pizza, ingredient)
            } else {
                pizza
            }
        }
    }

    private fun updatePizza(
        pizza: PizzaData,
        ingredient: Ingredient,
    ): PizzaData {
        val maxOrder = pizza.ingredients.maxOfOrNull { it.selectionOrder } ?: -1

        val updatedIngredients =
            pizza.ingredients.map { item ->
                if (item.id == ingredient.id) {
                    toggleIngredient(item, maxOrder)
                } else {
                    item
                }
            }

        return pizza.copy(ingredients = updatedIngredients)
    }

    private fun toggleIngredient(
        item: Ingredient,
        maxOrder: Int,
    ): Ingredient {
        val isSelected = !item.selected
        return item.copy(
            selected = isSelected,
            selectionOrder = if (isSelected) maxOrder + 1 else -1,
        )
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
