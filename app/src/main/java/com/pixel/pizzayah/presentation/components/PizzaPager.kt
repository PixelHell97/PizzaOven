package com.pixel.pizzayah.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pixel.pizzayah.presentation.model.PizzaData
import com.pixel.pizzayah.presentation.model.PizzaSize

@Composable
fun PizzaPager(
    pagerState: PagerState,
    pizzaList: List<PizzaData>,
    pizzaSize: PizzaSize,
    modifier: Modifier = Modifier,
) {
    val scale =
        animateFloatAsState(
            targetValue =
                when (pizzaSize) {
                    PizzaSize.SMALL -> 0.7f
                    PizzaSize.MEDIUM -> 0.8f
                    PizzaSize.LARGE -> 0.9f
                },
            label = "pizzaScale",
        )

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
    ) { page ->
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .size(300.dp)
                    .scale(scale.value),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(pizzaList[page].breadImage),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center),
            )
            pizzaList[page]
                .ingredients
                .filter { it.selected }
                .sortedBy { it.selectionOrder }
                .forEach { ingredient ->
                    PizzaToppings(images = ingredient.images)
                }
        }
    }
}
