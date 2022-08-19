package com.example.restaurantapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RestaurantsScreen() {
    val viewModel: RestaurantViewModel = viewModel()
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp
        )
    ) {
        items(viewModel.getRestaurants()) { restaurant ->
            RestaurantItem(restaurant)
        }
    }
}

@Composable
fun RestaurantItem(item: Restaurant) {
    val favoriteState = remember {
        mutableStateOf(false)
    }

    val icon = if (favoriteState.value) Icons.Filled.Favorite
               else Icons.Filled.FavoriteBorder

    Card(elevation = 4.dp,
         modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f)
            ) {}
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.weight(0.70f)
            )
            RestaurantIcon(
                icon,
                Modifier.weight(0.15f)) {
                favoriteState.value = !favoriteState.value
            }
        }
    }
}

@Composable
fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.h6)
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun RestaurantIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Image(imageVector = icon,
          contentDescription = "Restaurant icon",
          modifier = modifier
              .padding(8.dp)
              .clickable {
                  onClick()
              }
    )
}
