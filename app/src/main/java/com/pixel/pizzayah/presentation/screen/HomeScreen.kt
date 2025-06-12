package com.pixel.pizzayah.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixel.pizzayah.R
import com.pixel.pizzayah.presentation.components.IngredientButton
import com.pixel.pizzayah.presentation.components.PizzaPager
import com.pixel.pizzayah.presentation.components.PizzaTopAppBar
import com.pixel.pizzayah.presentation.model.Ingredient
import com.pixel.pizzayah.presentation.model.PizzaData
import com.pixel.pizzayah.presentation.model.PizzaSize

@Composable
fun HomeScreen(
    viewModel: PizzaViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.pizzaDataState.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0) { 5 }
    viewModel.setSelectedPizza(pagerState.settledPage)

    HomeContent(
        pagerState = pagerState,
        pizzaUiState = uiState.value,
        onPizzaSizeClicked = viewModel::onPizzaSizeClicked,
        onIngredientClicked = viewModel::onIngredientClicked,
        selectedPizza = uiState.value.selectedPizza,
        modifier = modifier,
    )
}

@Composable
fun HomeContent(
    pizzaUiState: HomeScreenUiState,
    onPizzaSizeClicked: (PizzaSize) -> Unit,
    onIngredientClicked: (Ingredient) -> Unit,
    selectedPizza: PizzaData,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(Color.White).verticalScroll(rememberScrollState()),
    ) {
        PizzaTopAppBar(
            modifier =
                Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 16.dp),
        )

        PizzaPreparation(
            pagerState = pagerState,
            pizzaList = pizzaUiState.pizzaList,
            pizzaSize = pizzaUiState.selectedSize,
            modifier = Modifier.padding(top = 24.dp),
        )

        Text(
            text = "$17",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier =
                Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally),
        )

        PizzaSizeSection(
            selectedSize = pizzaUiState.selectedSize,
            pizzaSizes = PizzaSize.entries,
            onClick = onPizzaSizeClicked,
            modifier =
                Modifier
                    .padding(top = 24.dp, bottom = 16.dp)
                    .align(Alignment.CenterHorizontally),
        )

        Text(
            "CUSTOMIZE YOUR PIZZA",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier =
                Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
        )

        PizzaIngredients(
            pizza = selectedPizza,
            onIngredientClicked = onIngredientClicked,
            modifier = Modifier.padding(top = 24.dp),
        )

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3e312e)),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { /*TODO*/ },
            modifier =
                Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally),
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Add to cart",
                modifier = Modifier.padding(end = 8.dp),
            )
            Text(
                text = "ADD TO CART",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun PizzaSizeSection(
    selectedSize: PizzaSize,
    pizzaSizes: List<PizzaSize>,
    onClick: (PizzaSize) -> Unit,
    modifier: Modifier = Modifier,
) {
    val alignment =
        when (selectedSize) {
            PizzaSize.SMALL -> Alignment.CenterStart
            PizzaSize.MEDIUM -> Alignment.Center
            PizzaSize.LARGE -> Alignment.CenterEnd
        }
    Box(modifier = modifier) {
        Box(
            modifier =
                Modifier
                    .shadow(8.dp, CircleShape)
                    .align(alignment)
                    .size(50.dp)
                    .background(Color.White, CircleShape),
        )
        Row(
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .wrapContentWidth()
                    .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(50.dp),
        ) {
            pizzaSizes.forEach { size ->
                Text(
                    text = size.name.first().toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier =
                        Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) {
                            onClick(size)
                        },
                )
            }
        }
    }
}

@Composable
fun PizzaPreparation(
    pagerState: PagerState,
    pizzaList: List<PizzaData>,
    pizzaSize: PizzaSize,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.plate),
            contentDescription = "Pizza plate",
            modifier = modifier.size(300.dp),
        )

        PizzaPager(
            pagerState = pagerState,
            pizzaList = pizzaList,
            pizzaSize = pizzaSize,
            modifier =
                Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Center),
        )
    }
}

@Composable
fun PizzaIngredients(
    pizza: PizzaData,
    onIngredientClicked: (Ingredient) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        LazyRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(pizza.ingredients.size) { index ->
                IngredientButton(
                    imageRes = pizza.ingredients[index].previewImage,
                    onClick = { onIngredientClicked(pizza.ingredients[index]) },
                    selected = pizza.ingredients[index].selected,
                )
            }
        }
    }
}
