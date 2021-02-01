package com.example.restaurantapp.ui.reviews

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.ReviewDto
import com.example.restaurantapp.ui.base.BaseAdapter
import com.example.restaurantapp.ui.base.utils.SimpleDateFormatter

class ReviewsAdapter(private val listener: ClickListener) :
    BaseAdapter<ReviewDto>() {

    override fun bind(holder: RecyclerView.ViewHolder, item: ReviewDto?) {
        (holder as ReviewViewHolder).bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ReviewViewHolder.create(parent, listener)
    }

    override fun areItemsTheSame(oldItem: ReviewDto?, newItem: ReviewDto?): Boolean {
        return oldItem?.userFIO == newItem?.userFIO && oldItem?.restaurantName == newItem?.restaurantName
    }

    override fun areContentsTheSame(oldItem: ReviewDto?, newItem: ReviewDto?): Boolean {
        return oldItem == newItem
    }

    interface ClickListener {
        fun onClick(item: ReviewDto)
    }
}

class ReviewViewHolder(
    val view: View,
    private val listener: ReviewsAdapter.ClickListener
) :
    RecyclerView.ViewHolder(view) {
    private var item: ReviewDto? = null
    private val title: TextView = view.findViewById(R.id.title)
    private val message: TextView = view.findViewById(R.id.textTV)
    private val date: TextView = view.findViewById(R.id.date)


    fun bind(item: ReviewDto?) {
        this.item = item
        item?.let {
            title.text = title.context.getString(
                R.string.review_title_pattern,
                it.userFIO,
                it.restaurantName
            )
            message.text = it.message
            message.isVisible = !TextUtils.isEmpty(it.message)

            it.dateAdded?.let { dateValue ->
                date.text = SimpleDateFormatter.format(dateValue)
                date.isVisible = true
            } ?: date.apply { isVisible = false }


            title.setCompoundDrawablesWithIntrinsicBounds(
                if (it.isPositive == true) {
                    R.drawable.ic_thumb_up_24dp
                } else {
                    R.drawable.ic_thumb_down_24dp
                }, 0, 0, 0
            )
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            listener: ReviewsAdapter.ClickListener
        ): ReviewViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_review, parent, false)
            return ReviewViewHolder(view, listener)
        }
    }
}