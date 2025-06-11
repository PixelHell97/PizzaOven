package com.pixel.pizzayah.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixel.pizzayah.R

@Composable
fun PizzaTopAppBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
        )

        Text(
            text = "Pizza",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
        )
    }
}

@Preview(name = "PizzaTopAppBar")
@Composable
private fun PreviewPizzaTopAppBar() {
    PizzaTopAppBar()
}
