package com.example.restaurantapp.core.dto

import com.google.gson.annotations.SerializedName

data class RestaurantsDto(
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Logo")
    val logo: String?,
    @SerializedName("MinCost")
    val minCost: String?,
    @SerializedName("DeliveryCost")
    val deliveryCost: String?,
    @SerializedName("DeliveryTime")
    val deliveryTime: String?,
    @SerializedName("PositiveReviews")
    val positiveReviews: String?,
    @SerializedName("ReviewsCount")
    val reviewsCount: String?,
    @SerializedName("Specializations")
    val specializations: List<Specializations>?
)

data class Specializations(
    @SerializedName("Name")
    val name: String?
)
