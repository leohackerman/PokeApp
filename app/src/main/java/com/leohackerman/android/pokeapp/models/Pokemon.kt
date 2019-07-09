package com.leohackerman.android.pokeapp.models

data class Pokemon(val id: Int,
                   val name: String,
                   val base_experience: Int,
                   val sprites: Sprites,
                   val height: Int,
                   val weight: Int,
                   val types:List<TypeDefinition>)