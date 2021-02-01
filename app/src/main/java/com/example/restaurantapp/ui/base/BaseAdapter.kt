package com.example.restaurantapp.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bind(holder, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun getItem(position: Int): T? {
        val itemList = items
        return if (position >= 0 && position < itemList.size) itemList[position] else null
    }

    fun replace(newItems: List<T>?) {
        when {
            items.isEmpty() -> {
                if (newItems == null) {
                    return
                }
                copyList(newItems)
                notifyDataSetChanged()
            }
            newItems == null -> {
                val oldSize = items.size
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> {
                val oldItems = items
                val diffCallback = object : DiffCallback<T>(oldItems, newItems) {
                    override fun areItemsTheSame(
                        oldItemPosition: Int,
                        newItemPosition: Int
                    ): Boolean {
                        return areItemsTheSame(getItem(oldItemPosition), newItems[newItemPosition])
                    }

                    override fun areContentsTheSame(
                        oldItemPosition: Int,
                        newItemPosition: Int
                    ): Boolean {
                        return areContentsTheSame(
                            getItem(oldItemPosition),
                            newItems[newItemPosition]
                        )
                    }

                }
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                copyList(newItems)
                diffResult.dispatchUpdatesTo(this)
            }
        }
    }

    private fun copyList(update: List<T>) {
        if (items.isNotEmpty()) {
            items.clear()
        }
        items.addAll(update)
    }

    abstract fun bind(holder: RecyclerView.ViewHolder, item: T?)
    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun areItemsTheSame(oldItem: T?, newItem: T?): Boolean
    abstract fun areContentsTheSame(oldItem: T?, newItem: T?): Boolean
}