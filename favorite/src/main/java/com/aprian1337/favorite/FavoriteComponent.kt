package com.aprian1337.favorite

import android.content.Context
import com.aprian1337.batique.di.FavDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavDependencies::class])
interface FavoriteComponent {
    fun inject(act: FavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(dependency: FavDependencies): Builder
        fun build(): FavoriteComponent
    }
}