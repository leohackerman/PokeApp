package com.leohackerman.android.pokeapp


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.leohackerman.android.pokeapp.databinding.FragmentHomeBinding
import com.leohackerman.android.pokeapp.utils.UIUtils
import com.leohackerman.android.pokeapp.viewmodel.PokeApiViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: PokeApiViewModel


    val MAX_STAT = 180
    val MIN_STAT = 1
    val NUM_OF_STATS = 5


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val view = binding.root
        viewModel = ViewModelProviders.of(this).get(PokeApiViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeData()

    }

    private fun setListeners(){
        search_input.setOnEditorActionListener{textInput, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                UIUtils.hideKeyboard(requireActivity())
                viewModel.searchPokemon(textInput.text.toString())
            }
            true
        }
    }


    private fun observeData(){
        viewModel.pokemon.observe(this, Observer {
            UIUtils.loadImageFromUrl(this,viewModel.getAvatarFrontUrl(),default_avatar)
            configureStatsChart()
        })
    }


    private fun configureStatsChart(){
        statsChart.description.isEnabled = false
        statsChart.webLineWidth= 1f
        statsChart.webColor = Color.GRAY
        statsChart.alpha = 1F



        statsChart.description.isEnabled = false

        val xAxis:XAxis = statsChart.xAxis
        val labels:Array<String> = arrayOf("Attack","Defense","Sp. Atk", "Sp. Def", "Speed")
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        val yAxis:YAxis = statsChart.yAxis
        yAxis.setLabelCount(5,false)
        yAxis.setDrawLabels(false)
        yAxis.axisMinimum=50f
        yAxis.axisMaximum-290f



        setData(viewModel.getTypeColor())

        statsChart.animateXY(1600,1600, Easing.EaseInOutQuad, Easing.EaseInOutQuad)

    }


    private fun setData(pokemonType:String){
        val dataValues:ArrayList<RadarEntry> = ArrayList()
        dataValues.add(RadarEntry(130f))
        dataValues.add(RadarEntry(150f))
        dataValues.add(RadarEntry(120f))
        dataValues.add(RadarEntry(180f))
        dataValues.add(RadarEntry(140f))
        val radarDataSet:RadarDataSet = RadarDataSet(dataValues,"Base Stats")
        radarDataSet.color = UIUtils.getTypeColor(resources,pokemonType)
        radarDataSet.fillColor = UIUtils.getTypeColor(resources,pokemonType)
        radarDataSet.setDrawFilled(true)

        val radarData:RadarData = RadarData()
        radarData.addDataSet(radarDataSet)
        statsChart.data = radarData

    }

}
