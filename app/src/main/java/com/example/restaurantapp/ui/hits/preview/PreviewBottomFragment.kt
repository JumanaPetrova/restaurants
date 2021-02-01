package com.example.restaurantapp.ui.hits.preview

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.R
import com.example.restaurantapp.core.dto.HitDto
import com.example.restaurantapp.injection.component.Injectable
import com.example.restaurantapp.ui.base.GlideApp
import com.example.restaurantapp.ui.base.utils.injectViewModel
import com.example.restaurantapp.ui.hits.HitsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import javax.inject.Inject


class PreviewBottomFragment : BottomSheetDialogFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HitsViewModel by lazy {
        injectViewModel(viewModelFactory)
    }
    private var bottomBehavior: BottomSheetBehavior<FrameLayout>? = null

    lateinit var item: HitDto

    companion object {
        private const val DATA_EXTRA = "DATA_EXTRA"

        fun newInstance(
            item: HitDto
        ): PreviewBottomFragment {
            val fragment = PreviewBottomFragment()
            val args = Bundle()
            args.putParcelable(DATA_EXTRA, item)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnDismissListener {}
        dialog.setOnShowListener {
            view?.let {
                initData()
            }

            val bottomSheet =
                dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)


            bottomSheet?.let {
                BottomSheetBehavior.from(it).apply {
                    bottomBehavior = this
                }
            }
        }
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.layout_bottom_sheet, container,
            false
        )
    }

    private fun initData() {
        arguments?.getParcelable<HitDto>(DATA_EXTRA)?.let { item ->
            this.item = item

            description.text = item.productDescription
            price.text = getString(R.string.price_pattern, item.productPrice)

            val glide = GlideApp.with(this)
            if (item.productImage?.startsWith("http") == true
                || item.productImage?.startsWith("https") == true
            ) {
                glide.load(item.productImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_default)
                    .into(logo)
            } else {
                glide.clear(logo)
            }
        }
    }
}