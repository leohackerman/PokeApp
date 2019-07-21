package com.leohackerman.android.pokeapp.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.leohackerman.android.pokeapp.R
import com.leohackerman.android.pokeapp.adapters.MoveRecyclerViewAdapter
import com.leohackerman.android.pokeapp.models.MoveDefinition
import com.leohackerman.android.pokeapp.models.Pokemon
import kotlinx.android.synthetic.main.fragment_moves.*



/**
 * A simple [Fragment] subclass.
 *
 */
class MovesFragment : Fragment() {

    private lateinit var listOfMoves : List<MoveDefinition>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val pokemon = arguments?.getSerializable("pokemon") as Pokemon
        listOfMoves = pokemon.moves
        return inflater.inflate(R.layout.fragment_moves, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_moves_list.layoutManager = LinearLayoutManager(context)
        rv_moves_list.adapter = MoveRecyclerViewAdapter(listOfMoves)
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.about_menu)
        if (item != null)
            item.isVisible = false
    }
}
