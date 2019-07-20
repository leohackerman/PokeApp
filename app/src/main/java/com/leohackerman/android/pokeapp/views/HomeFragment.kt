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
import com.leohackerman.android.pokeapp.databinding.FragmentHomeBinding
import com.leohackerman.android.pokeapp.utils.UIUtils
import com.leohackerman.android.pokeapp.viewmodel.PokeApiViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: PokeApiViewModel



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
                search_input.isEnabled = false
                progressBar.visibility = View.VISIBLE
            }
            true
        }
    }

    /**
     * Observers to the VM changes
     */
    private fun observeData(){
        viewModel.pokemon.observe(this, Observer {
            progressBar.visibility = View.GONE
            statsChart.visibility = View.VISIBLE
            search_input.isEnabled = true
            UIUtils.loadImageFromUrl(this,viewModel.getAvatarFrontUrl(),default_avatar)
            drawStatsChart()
            if(viewModel.pokemon.value!!.types.size>1){
                    pokeType2.visibility = View.VISIBLE
                }
            else{
                pokeType2.visibility = View.INVISIBLE
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            progressBar.visibility = View.GONE
            statsChart.visibility = View.INVISIBLE
            search_input.isEnabled = true
            UIUtils.showOneButtonDialog(context,"Error",viewModel.errorMessage.value,android.R.string.ok)
        })
    }

    /**
     * Draws the stats chart
     */
    private fun drawStatsChart(){
        statsChart.description.isEnabled = false
        statsChart.webLineWidth= 1f
        statsChart.webColor = Color.GRAY
        statsChart.alpha = 1F
        statsChart.description.isEnabled = false
        val xAxis:XAxis = statsChart.xAxis
        val labels:Array<String> = arrayOf("Attack","Defense","Sp. Atk", "Sp. Def", "Speed","Hp")
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        val yAxis:YAxis = statsChart.yAxis
        yAxis.setLabelCount(6,false)
        yAxis.setDrawLabels(false)
        yAxis.axisMinimum=50f
        setData(viewModel.getTypeColor())
        statsChart.animateXY(1600,1600, Easing.EaseInOutQuad, Easing.EaseInOutQuad)
    }


    private fun setData(pokemonType:String){
        val dataValues:ArrayList<RadarEntry> = ArrayList()
        dataValues.add(RadarEntry(viewModel.pokemon.value!!.stats[4].base_stat.toFloat()))
        dataValues.add(RadarEntry(viewModel.pokemon.value!!.stats[3].base_stat.toFloat()))
        dataValues.add(RadarEntry(viewModel.pokemon.value!!.stats[2].base_stat.toFloat()))
        dataValues.add(RadarEntry(viewModel.pokemon.value!!.stats[1].base_stat.toFloat()))
        dataValues.add(RadarEntry(viewModel.pokemon.value!!.stats[0].base_stat.toFloat()))
        dataValues.add(RadarEntry(viewModel.pokemon.value!!.stats[5].base_stat.toFloat()))
        val radarDataSet:RadarDataSet = RadarDataSet(dataValues,"Base Stats")
        radarDataSet.color = UIUtils.getTypeColor(resources,pokemonType)
        radarDataSet.fillColor = UIUtils.getTypeColor(resources,pokemonType)
        radarDataSet.setDrawFilled(true)
        val radarData:RadarData = RadarData()
        radarData.addDataSet(radarDataSet)
        statsChart.data = radarData
    }

}
