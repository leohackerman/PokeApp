package com.leohackerman.android.pokeapp.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import com.leohackerman.android.pokeapp.R


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_about, container, false)
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.about_menu)
        if (item != null)
            item.isVisible = false
    }


}
