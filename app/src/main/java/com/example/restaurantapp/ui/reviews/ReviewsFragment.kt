package com.example.restaurantapp.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.ReviewDto
import com.example.restaurantapp.ui.base.BaseFragment
import com.example.restaurantapp.ui.base.VerticalDividerItemDecoration
import com.example.restaurantapp.ui.base.utils.ViewUtil
import com.example.restaurantapp.ui.base.utils.injectViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment : BaseFragment() {

    private lateinit var adapter: ReviewsAdapter
    private val viewModel: ReviewsViewModel by lazy {
        injectViewModel(viewModelFactory)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ReviewsAdapter(object : ReviewsAdapter.ClickListener {
            override fun onClick(item: ReviewDto) {

            }
        })
        reviewsList.adapter = adapter
        reviewsList.addItemDecoration(
            VerticalDividerItemDecoration(
                0,
                ContextCompat.getDrawable(reviewsList.context, R.drawable.ic_divider_elements)
            )
        )
        swipe_container.setOnRefreshListener {
            getData()
        }

        swipe_container.setColorSchemeResources(
            R.color.colorAccent,
            R.color.colorAccent,
            R.color.colorAccent
        )
        getData()
    }

    private fun getData() {
        mDisposable.add(
            viewModel.reviews
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    swipe_container.isRefreshing = false
                    adapter.replace(it)
                }, {
                    swipe_container.isRefreshing = false
                })
        )
    }
}