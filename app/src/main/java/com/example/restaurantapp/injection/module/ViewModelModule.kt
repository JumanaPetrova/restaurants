package com.example.restaurantapp.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.injection.scope.ViewModelKey
import com.example.restaurantapp.ui.base.CustomViewModelFactory
import com.example.restaurantapp.ui.hits.HitsViewModel
import com.example.restaurantapp.ui.restaurants.RestaurantsViewModel
import com.example.restaurantapp.ui.reviews.ReviewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * ViewModelModule
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ReviewsViewModel::class)
    internal abstract fun bindReviewsViewModel(viewModel: ReviewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HitsViewModel::class)
    internal abstract fun bindHitsViewModel(viewModel: HitsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    internal abstract fun bindRestaurantsViewModel(viewModel: RestaurantsViewModel): ViewModel
}
