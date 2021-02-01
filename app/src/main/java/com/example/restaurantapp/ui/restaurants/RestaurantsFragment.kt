package com.example.restaurantapp.ui.restaurants

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.RestaurantsDto
import com.example.restaurantapp.ui.base.BaseFragment
import com.example.restaurantapp.ui.base.GlideApp
import com.example.restaurantapp.ui.base.VerticalDividerItemDecoration
import com.example.restaurantapp.ui.base.utils.ViewUtil
import com.example.restaurantapp.ui.base.utils.injectViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_restaurants.*


class RestaurantsFragment : BaseFragment() {

    private lateinit var adapter: RestaurantAdapter
    private val viewModel: RestaurantsViewModel by lazy {
        injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val glide = GlideApp.with(this)
        adapter = RestaurantAdapter(glide, object : RestaurantAdapter.ClickListener {
            override fun onClick(item: RestaurantsDto) {

            }
        })
        restaurantsList.adapter = adapter
        restaurantsList.addItemDecoration(
            VerticalDividerItemDecoration(
                ViewUtil.dpToPx(16)
            )
        )

        mDisposable.add(
            viewModel.restaurants
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.replace(it)
                }, {

                })
        )
        appBar.setExpanded(false)

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (TextUtils.isEmpty(newText)) {
                    search(null)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (TextUtils.isEmpty(query)) {
                    ViewUtil.hideKeyboard(activity)
                } else {
                    val prevQuery = viewModel.filterData.query
                    if (prevQuery != query && !TextUtils.isEmpty(query)) {
                        search(query)
                    }
                }
                return true
            }

        })
    }

    private fun search(query: String?) {
        mDisposable.add(
            viewModel.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.replace(it)
                }, {

                })
        )
    }
}