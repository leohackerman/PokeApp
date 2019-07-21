package com.leohackerman.android.pokeapp.base

import androidx.lifecycle.ViewModel
import com.leohackerman.android.pokeapp.injection.DaggerViewModelInjector
import com.leohackerman.android.pokeapp.injection.ViewModelInjector
import com.leohackerman.android.pokeapp.injection.module.NetworkModule
import com.leohackerman.android.pokeapp.viewmodel.PokeApiViewModel


abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()

    }

    private fun inject(){
        when (this){
            is PokeApiViewModel -> injector.inject(this)
        }
    }

}