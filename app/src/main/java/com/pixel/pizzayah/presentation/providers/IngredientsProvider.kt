package com.pixel.pizzayah.presentation.providers

import androidx.compose.ui.res.stringResource
import com.pixel.pizzayah.R
import com.pixel.pizzayah.presentation.model.Ingredient
import com.pixel.pizzayah.presentation.utils.Constants.BASIL
import com.pixel.pizzayah.presentation.utils.Constants.BROCCOLI
import com.pixel.pizzayah.presentation.utils.Constants.MAX_IMAGES
import com.pixel.pizzayah.presentation.utils.Constants.MUSHROOM
import com.pixel.pizzayah.presentation.utils.Constants.ONION
import com.pixel.pizzayah.presentation.utils.Constants.SAUSAGE

class IngredientsProvider {
    private val ingredientImageSets = mapOf(
        BASIL to generateImageSet(
            R.drawable.basil_1,
            R.drawable.basil_10
        ),
        BROCCOLI to generateImageSet(
            R.drawable.broccoli_1,
            R.drawable.broccoli_10
        ),
        MUSHROOM to generateImageSet(
            R.drawable.mushroom_1,
            R.drawable.mushroom_10
        ),
        ONION to generateImageSet(
            R.drawable.onion_1,
            R.drawable.onion_10
        ),
        SAUSAGE to generateImageSet(
            R.drawable.sausage_1,
            R.drawable.sausage_10
        )
    )

    private fun generateImageSet(start: Int, end: Int): List<Int> {
        return List(MAX_IMAGES) { index -> start + (index % (end - start + 1)) }
    }

    val ingredients = listOf(
        Ingredient(
            id = 1,
            previewImage = R.drawable.basil_3,
            images = ingredientImageSets[BASIL] ?: emptyList()
        ),
        Ingredient(
            id = 2,
            previewImage = R.drawable.broccoli_3,
            images = ingredientImageSets[BROCCOLI] ?: emptyList()
        ),
        Ingredient(
            id = 3,
            previewImage = R.drawable.mushroom_3,
            images = ingredientImageSets[MUSHROOM] ?: emptyList()
        ),
        Ingredient(
            id = 4,
            previewImage = R.drawable.onion_3,
            images = ingredientImageSets[ONION] ?: emptyList()
        ),
        Ingredient(
            id = 5,
            previewImage = R.drawable.sausage_3,
            images = ingredientImageSets[SAUSAGE] ?: emptyList()
        )
    )
}