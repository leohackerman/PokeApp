package com.leohackerman.android.pokeapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
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
    }
}