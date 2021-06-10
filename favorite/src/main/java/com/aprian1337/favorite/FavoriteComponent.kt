package com.aprian1337.favorite

import android.content.Context
import com.aprian1337.batique.di.FavDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavDependencies::class])
interface FavoriteComponent {
    fun inject(activity: FavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(dependency: FavDependencies): Builder
        fun build(): FavoriteComponent
    }
}