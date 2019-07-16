package com.leohackerman.android.pokeapp.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.fragment_home.*

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
                apply(RequestOptions().fitCenter()).
                into(imageView)
        }
    }
}