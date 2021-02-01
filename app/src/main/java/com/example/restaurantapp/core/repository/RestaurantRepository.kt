package com.example.restaurantapp.core.repository

import com.example.restaurantapp.core.api.RestService
import com.example.restaurantapp.core.api.ServerProblemException
import com.example.restaurantapp.core.api.response.ApiErrorResponse
import com.example.restaurantapp.core.api.response.ApiResponse
import com.example.restaurantapp.core.api.response.ApiSuccessResponse
import com.example.restaurantapp.core.dto.HitDto
import com.example.restaurantapp.core.dto.RestaurantsDto
import com.example.restaurantapp.core.dto.ReviewDto
import io.reactivex.Observable
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val restService: RestService
) {

    fun filterRestaurant(query: String?): Observable<MutableList<RestaurantsDto>> =
        loadRestaurants()
            .flatMap {
                Observable.fromIterable(it)
            }
            .filter {
                query == null || it.name?.contains(query, true) == true
            }.toList().toObservable()


    fun loadRestaurants(): Observable<List<RestaurantsDto>> {
        return restService.getRestaurants().map { responseApi ->
            when (val response = ApiResponse.create(responseApi)) {
                is ApiSuccessResponse -> {
                    if (response.body.isNotEmpty()) {
                        response.body
                    } else {
                        mutableListOf()
                    }
                }
                is ApiErrorResponse -> {
                    throw ServerProblemException(response.error.message)
                }
                else -> {
                    mutableListOf()
                }
            }
        }
    }

    fun loadReviews(): Observable<List<ReviewDto>> {
        return restService.getReviews().map { responseApi ->
            when (val response = ApiResponse.create(responseApi)) {
                is ApiSuccessResponse -> {
                    if (response.body.isNotEmpty()) {
                        response.body
                    } else {
                        mutableListOf()
                    }
                }
                is ApiErrorResponse -> {
                    throw ServerProblemException(response.error.message)
                }
                else -> {
                    mutableListOf()
                }
            }
        }
    }

    fun loadHits(): Observable<List<HitDto>> {
        return restService.getHits().map { responseApi ->
            when (val response = ApiResponse.create(responseApi)) {
                is ApiSuccessResponse -> {
                    if (response.body.isNotEmpty()) {
                        response.body
                    } else {
                        mutableListOf()
                    }
                }
                is ApiErrorResponse -> {
                    throw ServerProblemException(response.error.message)
                }
                else -> {
                    mutableListOf()
                }
            }
        }
    }
}