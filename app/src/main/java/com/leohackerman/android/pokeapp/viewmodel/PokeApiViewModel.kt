package com.leohackerman.android.pokeapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.leohackerman.android.pokeapp.base.BaseViewModel
import com.leohackerman.android.pokeapp.models.Pokemon
import com.leohackerman.android.pokeapp.models.Type
import com.leohackerman.android.pokeapp.network.PokeApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Error
import javax.inject.Inject

class PokeApiViewModel:BaseViewModel() {
    @Inject
    lateinit var pokeApi: PokeApi



    private lateinit var subscription: Disposable
    val pokemon:MutableLiveData<Pokemon> = MutableLiveData()
    val type0:MutableLiveData<Type> = MutableLiveData()
    val type1:MutableLiveData<Type> = MutableLiveData()
    val errorMessage:MutableLiveData<String> = MutableLiveData()

    var pokeColor:String = String()


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
        type0.value = result.types[0].type
        type1.value = result.types[1].type
        pokeColor = result.types[0].type.name
    }

    private fun onSearchFailed(error: Throwable){
        errorMessage.value = "Pokemon not found"

    }

    fun getAvatarFrontUrl(): String? {
        return pokemon.value?.sprites?.front_default
    }


    fun getTypeColor():String{
        return pokemon.value!!.types[0].type.name
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}