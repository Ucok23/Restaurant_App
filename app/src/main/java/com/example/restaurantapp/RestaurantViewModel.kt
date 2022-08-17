package com.example.restaurantapp

import androidx.lifecycle.ViewModel

class RestaurantViewModel() : ViewModel() {
    fun getRestaurants() = dummyRestaurants
}