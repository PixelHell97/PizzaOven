package com.pixel.pizzayah.presentation.model

import androidx.annotation.DrawableRes

data class Ingredient(
    val id: Int,
    @DrawableRes
    val previewImage: Int,
    @DrawableRes
    val images: List<Int> = emptyList(),
    val selected: Boolean = false,
    val selectionOrder: Int = -1,
)
