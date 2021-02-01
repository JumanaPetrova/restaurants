package com.example.restaurantapp.ui.reviews

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.core.repository.RestaurantRepository
import javax.inject.Inject

class ReviewsViewModel @Inject constructor(
    restaurantRepository: RestaurantRepository
) : ViewModel() {
    val reviews = restaurantRepository.loadReviews()
}