package com.example.restaurantapp.ui.hits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.HitDto
import com.example.restaurantapp.ui.base.BaseAdapter
import com.example.restaurantapp.ui.base.GlideRequests
import com.google.android.material.button.MaterialButton

class HitAdapter(private val glide: GlideRequests, private val listener: ClickListener) :
    BaseAdapter<HitDto>() {

    override fun bind(holder: RecyclerView.ViewHolder, item: HitDto?) {
        (holder as HitViewHolder).bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HitViewHolder.create(parent, glide, listener)
    }

    override fun areItemsTheSame(oldItem: HitDto?, newItem: HitDto?): Boolean {
        return oldItem?.productName == newItem?.productName && newItem?.restaurantId == oldItem?.restaurantId
    }

    override fun areContentsTheSame(oldItem: HitDto?, newItem: HitDto?): Boolean {
        return oldItem == newItem
    }

    interface ClickListener {
        fun onClick(item: HitDto)
        fun onLogoClick(item: HitDto)
    }
}

class HitViewHolder(
    view: View,
    private val glide: GlideRequests,
    private val listener: HitAdapter.ClickListener
) :
    RecyclerView.ViewHolder(view) {
    private var item: HitDto? = null
    private val logo: ImageView = view.findViewById(R.id.logo)
    private val title: TextView = view.findViewById(R.id.title)
    private val description: TextView = view.findViewById(R.id.description)
    private val restaurantName: TextView = view.findViewById(R.id.restaurantName)
    private val restaurantLogo: ImageView = view.findViewById(R.id.restLogo)
    private val takeBtn: MaterialButton = view.findViewById(R.id.takeBtn)
    private val container: View = view.findViewById(R.id.container)

    fun bind(item: HitDto?) {
        this.item = item
        item?.let {
            title.text = it.productName
            description.text = it.productDescription
            takeBtn.text = takeBtn.context.getString(R.string.price_pattern, it.productPrice)
            restaurantName.text = it.restaurantName

            if (it.productImage?.startsWith("http") == true
                || it.productImage?.startsWith("https") == true
            ) {
                glide.load(it.productImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_default)
                    .into(logo)
            } else {
                glide.clear(logo)
            }

            if (it.restaurantLogo?.startsWith("http") == true
                || it.restaurantLogo?.startsWith("https") == true
            ) {
                glide.load(it.restaurantLogo)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_default)
                    .into(restaurantLogo)
            } else {
                glide.clear(restaurantLogo)
            }
            logo.setOnLongClickListener {
                listener.onLogoClick(item)
                true
            }
            container.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: GlideRequests,
            listener: HitAdapter.ClickListener
        ): HitViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_hit, parent, false)
            return HitViewHolder(view, glide, listener)
        }
    }
}