package com.leohackerman.android.pokeapp.views


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.TextView
import com.leohackerman.android.pokeapp.R
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        about_author.setOnClickListener{
            val myTextView = it as TextView
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/leohackerman")
            startActivity(openURL)
        }

        about_message.setOnClickListener {
            val myTextView = it as TextView
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://pokeapi.co")
            startActivity(openURL)
        }


    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.about_menu)
        if (item != null)
            item.isVisible = false
    }


}
