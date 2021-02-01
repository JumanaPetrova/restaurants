package com.example.restaurantapp.injection.module


import com.example.restaurantapp.ui.hits.HitsFragment
import com.example.restaurantapp.ui.hits.preview.PreviewBottomFragment
import com.example.restaurantapp.ui.restaurants.RestaurantsFragment
import com.example.restaurantapp.ui.reviews.ReviewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * FragmentBuildersModule
 *
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeRestaurantsFragment(): RestaurantsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeHitsFragment(): HitsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeReviewsFragment(): ReviewsFragment

    @ContributesAndroidInjector
    internal abstract fun contributePreviewBottomFragment(): PreviewBottomFragment
}