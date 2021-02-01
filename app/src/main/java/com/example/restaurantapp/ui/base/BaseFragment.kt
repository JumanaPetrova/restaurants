package com.example.restaurantapp.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val mDisposable = CompositeDisposable()

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStop() {
        super.onStop()
        mDisposable.clear()
    }

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }
}