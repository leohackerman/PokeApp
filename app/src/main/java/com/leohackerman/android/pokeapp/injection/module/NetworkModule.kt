package com.leohackerman.android.pokeapp.injection.module

import com.leohackerman.android.pokeapp.network.PokeApi
import com.leohackerman.android.pokeapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkModule {
    @Module
    @Suppress("unused")
    object NetworkModule{

        @Provides
        @Reusable
        @JvmStatic
        internal fun providePokeApi(retrofit: Retrofit):PokeApi{
            return retrofit.create(PokeApi::class.java)
        }

        @Provides
        @Reusable
        @JvmStatic
        internal fun provideRetrofitInterface():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
    }
}