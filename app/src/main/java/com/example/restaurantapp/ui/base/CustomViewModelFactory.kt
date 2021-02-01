package com.example.restaurantapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Factory that provides `ViewModels` for a scope
 *
 */
@Singleton
class CustomViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            var creator: Provider<ViewModel>? = creators[modelClass]
            if (creator == null) {
                for ((key, value) in creators) {
                    if (modelClass.isAssignableFrom(key)) {
                        creator = value
                        break
                    }
                }
            }
            if (creator == null) throw IllegalArgumentException("unknown model class $modelClass")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
