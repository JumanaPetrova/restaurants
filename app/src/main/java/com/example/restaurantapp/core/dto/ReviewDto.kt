package com.example.restaurantapp.core.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class ReviewDto(
    @SerializedName("IsPositive")
    val isPositive: Boolean?,
    @SerializedName("Message")
    val message: String?,
    @SerializedName("DateAdded")
    val dateAdded: Date?,
    @SerializedName("UserFIO")
    val userFIO: String?,
    @SerializedName("RestaurantName")
    val restaurantName: String?
)
