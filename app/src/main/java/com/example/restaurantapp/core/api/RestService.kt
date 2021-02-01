package com.example.restaurantapp.core.api

import com.example.restaurantapp.core.dto.HitDto
import com.example.restaurantapp.core.dto.RestaurantsDto
import com.example.restaurantapp.core.dto.ReviewDto
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface RestService {
    @GET("restaurants")
    fun getRestaurants(): Observable<Response<List<RestaurantsDto>>>

    @GET("reviews")
    fun getReviews(): Observable<Response<List<ReviewDto>>>

    @GET("hits")
    fun getHits(): Observable<Response<List<HitDto>>>
}