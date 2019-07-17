package com.leohackerman.android.pokeapp.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.leohackerman.android.pokeapp.R
import io.reactivex.annotations.NonNull

class UIUtils {
    companion object{
        fun hideKeyboard(@NonNull activity: Activity){
            val view = activity.currentFocus
            if(view != null){
                val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        fun loadImageFromUrl(@NonNull fragment: Fragment, url:String?, imageView: ImageView){
            Glide.with(fragment).
                load(url).
                into(imageView)
        }


        fun getTypeColor(resources: Resources, type:String): Int {
            when(type){
                "normal" -> return resources.getColor(R.color.normal)
                "fire" -> return resources.getColor(R.color.fire)
                "fighting" -> return resources.getColor(R.color.fighting)
                "water" -> return resources.getColor(R.color.water)
                "flying" -> return resources.getColor(R.color.flying)
                "grass" -> return resources.getColor(R.color.grass)
                "poison" -> return resources.getColor(R.color.poison)
                "electric" -> return resources.getColor(R.color.electric)
                "ground" -> return resources.getColor(R.color.ground)
                "psychic" -> return resources.getColor(R.color.psychic)
                "rock" -> return resources.getColor(R.color.rock)
                "ice" -> return resources.getColor(R.color.ice)
                "bug" -> return resources.getColor(R.color.bug)
                "dragon" -> return resources.getColor(R.color.dragon)
                "ghost" -> return resources.getColor(R.color.ghost)
                "dark" -> return resources.getColor(R.color.dark)
                "steel" -> return resources.getColor(R.color.steel)
                "fairy" -> return resources.getColor(R.color.fairy)
                else -> return resources.getColor(R.color.normal)
            }
        }
    }
}