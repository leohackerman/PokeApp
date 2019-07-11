package com.leohackerman.android.pokeapp.injection

import com.leohackerman.android.pokeapp.injection.module.NetworkModule
import com.leohackerman.android.pokeapp.viewmodel.PokeApiViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(pokeApiViewModel: PokeApiViewModel)
}