package com.leohackerman.android.pokeapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.util.Log
import android.util.MutableDouble
import com.leohackerman.android.pokeapp.base.BaseViewModel
import com.leohackerman.android.pokeapp.models.Pokemon
import com.leohackerman.android.pokeapp.network.PokeApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokeApiViewModel:BaseViewModel() {
    @Inject
    lateinit var pokeApi: PokeApi

    private lateinit var subscription: Disposable
    val pokemon:MutableLiveData<Pokemon> = MutableLiveData()

    fun searchPokemon(query:String){
        subscription = pokeApi.searchPokemon(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{onSearchStart()}
            .doOnTerminate { onSearchFinish() }
            .subscribe(
                {result -> onSearchSuccess(result)},
                {error -> onSearchFailed(error)}
            )
    }

    private fun onSearchStart(){

    }

    private fun onSearchFinish(){

    }

    private fun onSearchSuccess(result: Pokemon) {
        pokemon.value = result
    }

    private fun onSearchFailed(error: Throwable){


    }

    fun getAvatarFrontUrl(): String? {
        return pokemon.value?.sprites?.front_default
    }




    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}