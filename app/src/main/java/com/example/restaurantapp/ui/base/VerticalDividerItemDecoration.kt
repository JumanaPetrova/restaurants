package com.example.restaurantapp.ui.base

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Отступ от элементов списка в RecyclerView
 */
open class VerticalDividerItemDecoration(value: Int, private val itemDivider: Drawable? = null) :
    ItemDecoration() {
    private val space: Int = value
    private var reverse = false

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // skip first item in the list
        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }
        if (itemDivider == null) {
            if (reverse) {
                outRect[0, 0, 0] = space
            } else {
                outRect[0, space, 0] = 0
            }
        } else {
            outRect[0, itemDivider.intrinsicHeight, 0] = 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }
    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (itemDivider != null) {
            val dividerLeft = parent.paddingLeft + space
            val dividerRight = parent.width - parent.paddingRight
            val childCount = parent.childCount
            for (i in 0 until childCount - 1) {
                val child = parent.getChildAt(i)
                val params =
                    child.layoutParams as RecyclerView.LayoutParams
                val dividerTop = child.bottom + params.bottomMargin
                val dividerBottom = dividerTop + itemDivider.intrinsicHeight
                itemDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                itemDivider.draw(canvas)
            }
        } else {
            super.onDraw(canvas, parent, state)
        }
    }
}