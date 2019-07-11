package com.leohackerman.android.pokeapp.viewmodel

import android.util.Log
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
        Log.d("pokeapi",result.toString())
    }

    private fun onSearchFailed(error: Throwable){
        Log.d("error",error.toString())

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}