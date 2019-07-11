package com.leohackerman.android.pokeapp.network

import com.leohackerman.android.pokeapp.models.Pokemon
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("/api/v2/pokemon/{search_term}")
    fun searchPokemon(@Path("search_term") searchTerm:String):Observable<Pokemon>
}