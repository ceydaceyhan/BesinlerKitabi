package com.ceydaceyhan.besinlerkitab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ceydaceyhan.besinlerkitab.R
import com.ceydaceyhan.besinlerkitab.util.gorselIndir
import com.ceydaceyhan.besinlerkitab.util.placeholderYap
import com.ceydaceyhan.besinlerkitab.viewmodel.BesinDetayiViewModelTwo
import kotlinx.android.synthetic.main.besin_recycler_row.*
import kotlinx.android.synthetic.main.fragment_besin_detay.*
import kotlinx.android.synthetic.main.fragment_besin_detay.view.*

class BesinDetayFragment : Fragment() {
    private var besinId = 0
    private lateinit var viewModel: BesinDetayiViewModelTwo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            besinId = BesinDetayFragmentArgs.fromBundle(it).besinId

        }
        viewModel = ViewModelProviders.of(this).get(BesinDetayiViewModelTwo::class.java)
        viewModel.roomVerisiniAl(besinId)
        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin ->
            besin?.let {
                besinIsmi.text = it.besinIsim
                besinKarbonhidrat.text = it.besinKarbonhidrat
                besinYag.text = it.besinYag
                besinProtein.text = it.besinProtein
                besinKalori.text = it.besinKalori
                context?.let {
                    besinImage.gorselIndir(besin.besinGorsel, placeholderYap(it))
                }
            }
        })
    }
}