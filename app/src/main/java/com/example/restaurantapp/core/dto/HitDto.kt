package com.example.restaurantapp.core.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HitDto(
    @SerializedName("ProductName")
    val productName: String?,
    @SerializedName("ProductImage")
    val productImage: String?,
    @SerializedName("ProductPrice")
    val productPrice: Int?,
    @SerializedName("ProductDescription")
    val productDescription: String?,
    @SerializedName("RestaurantId")
    val restaurantId: Int?,
    @SerializedName("RestaurantName")
    val restaurantName: String?,
    @SerializedName("RestaurantLogo")
    val restaurantLogo: String?
) : Parcelable