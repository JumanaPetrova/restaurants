package com.example.restaurantapp.ui.restaurants

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.core.dto.RestaurantsDto
import com.example.restaurantapp.core.repository.RestaurantRepository
import io.reactivex.Observable
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    data class FilterData(var query: String?)

    val filterData = FilterData(null)
    val restaurants = restaurantRepository.loadRestaurants()

    fun search(query: String?): Observable<MutableList<RestaurantsDto>> =
        restaurantRepository.filterRestaurant(query).map {
            filterData.query = query
            it
        }
}