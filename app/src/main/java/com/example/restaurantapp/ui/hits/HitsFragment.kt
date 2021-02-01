package com.example.restaurantapp.ui.hits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.HitDto
import com.example.restaurantapp.ui.base.BaseFragment
import com.example.restaurantapp.ui.base.GlideApp
import com.example.restaurantapp.ui.base.VerticalDividerItemDecoration
import com.example.restaurantapp.ui.base.utils.ViewUtil
import com.example.restaurantapp.ui.base.utils.injectViewModel
import com.example.restaurantapp.ui.hits.preview.PreviewBottomFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_hits.*

class HitsFragment : BaseFragment() {

    private lateinit var adapter: HitAdapter
    private val viewModel: HitsViewModel by lazy {
        injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val glide = GlideApp.with(this)
        adapter = HitAdapter(glide, object : HitAdapter.ClickListener {
            override fun onClick(item: HitDto) {

            }

            override fun onLogoClick(item: HitDto) {
                val dialogFragment =
                    PreviewBottomFragment.newInstance(
                        item
                    )
                dialogFragment.show(
                    this@HitsFragment.childFragmentManager,
                    "detailed_preview"
                )
            }
        })
        hitsList.adapter = adapter
        hitsList.addItemDecoration(
            VerticalDividerItemDecoration(
                ViewUtil.dpToPx(8)
            )
        )

        mDisposable.add(
            viewModel.hits
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.replace(it)
                }, {

                })
        )
    }
}