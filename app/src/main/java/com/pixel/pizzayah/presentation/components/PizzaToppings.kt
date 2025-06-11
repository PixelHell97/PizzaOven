package com.pixel.pizzayah.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun PizzaToppings(images: List<Int>) {
    val scale = remember { Animatable(5f) }

    Box(
        modifier =
            Modifier
                .size(300.dp)
                .scale(scale.value),
    ) {
        images.forEach { image ->
            val randomOffsetX = remember { mutableIntStateOf(Random.nextInt(30, 230)) }
            val randomOffsetY = remember { mutableIntStateOf(Random.nextInt(30, 230)) }

            ToppingItem(
                imageRes = image,
                modifier = Modifier.offset(randomOffsetX.intValue.dp, randomOffsetY.intValue.dp),
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        scale.animateTo(targetValue = 1.0f)
    }
}

@Composable
fun ToppingItem(
    imageRes: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        painterResource(imageRes),
        contentDescription = null,
        modifier = modifier.size(45.dp),
    )
}
