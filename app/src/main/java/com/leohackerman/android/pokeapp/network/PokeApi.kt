package com.leohackerman.android.pokeapp.network

import com.leohackerman.android.pokeapp.models.Pokemon
import io.reactivex.Observable
import retrofit2.http.GET

interface PokeApi {
    @GET("/api/v2/pokemon/")
    fun searchPokemon():Observable<Pokemon>
}