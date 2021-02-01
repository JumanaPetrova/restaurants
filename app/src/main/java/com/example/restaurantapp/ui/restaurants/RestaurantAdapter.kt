package com.example.restaurantapp.ui.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.RestaurantsDto
import com.example.restaurantapp.ui.base.BaseAdapter
import com.example.restaurantapp.ui.base.GlideRequests
import com.example.restaurantapp.ui.base.utils.addRipple

class RestaurantAdapter(private val glide: GlideRequests, private val listener: ClickListener) :
    BaseAdapter<RestaurantsDto>() {

    override fun bind(holder: RecyclerView.ViewHolder, item: RestaurantsDto?) {
        (holder as RestaurantViewHolder).bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RestaurantViewHolder.create(parent, glide, listener)
    }

    override fun areItemsTheSame(oldItem: RestaurantsDto?, newItem: RestaurantsDto?): Boolean {
        return oldItem?.name == newItem?.name
    }

    override fun areContentsTheSame(oldItem: RestaurantsDto?, newItem: RestaurantsDto?): Boolean {
        return oldItem == newItem
    }

    interface ClickListener {
        fun onClick(item: RestaurantsDto)
    }
}

class RestaurantViewHolder(
    view: View,
    private val glide: GlideRequests,
    private val listener: RestaurantAdapter.ClickListener
) :
    RecyclerView.ViewHolder(view) {
    private var item: RestaurantsDto? = null
    private val logo: ImageView = view.findViewById(R.id.logoView)
    private val title: TextView = view.findViewById(R.id.title)
    private val specializations: TextView = view.findViewById(R.id.specializations)
    private val rating: TextView = view.findViewById(R.id.rating)
    private val container: View = view.findViewById(R.id.container)

    fun bind(item: RestaurantsDto?) {
        this.item = item
        item?.let {
            title.text = it.name
            specializations.text =
                it.specializations?.map { it.name }?.joinToString(separator = "/")
            rating.text = rating.context.getString(R.string.rating_pattern, it.positiveReviews)
            if (it.logo?.startsWith("http") == true
                || it.logo?.startsWith("https") == true
            ) {
                glide.load(it.logo)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_default)
                    .into(logo)
            } else {
                glide.clear(logo)
            }
            container.addRipple()
            container.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: GlideRequests,
            listener: RestaurantAdapter.ClickListener
        ): RestaurantViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_restaurant, parent, false)
            return RestaurantViewHolder(view, glide, listener)
        }
    }
}