package com.example.restaurantapp.ui.hits

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.core.repository.RestaurantRepository
import javax.inject.Inject

class HitsViewModel @Inject constructor(
    restaurantRepository: RestaurantRepository
) : ViewModel() {
    val hits = restaurantRepository.loadHits()
}