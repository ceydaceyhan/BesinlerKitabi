package com.ceydaceyhan.besinlerkitab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceydaceyhan.besinlerkitab.R
import com.ceydaceyhan.besinlerkitab.adapter.BesinRecyclerAdapter
import com.ceydaceyhan.besinlerkitab.viewmodel.BesinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*


class BesinListesiFragment : Fragment() {
    private lateinit var viewModel: BesinListesiViewModel
    private val recylerBesinAdapter = BesinRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refreshData()
        besinListRecyclerView.layoutManager = LinearLayoutManager(context)
        besinListRecyclerView.adapter = recylerBesinAdapter
        swipeRefreshLayout.setOnRefreshListener {
            besinYukleniyor.visibility = View.VISIBLE
            besinHataMesaji.visibility = View.GONE
            besinListRecyclerView.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.besinler.observe(viewLifecycleOwner, Observer {
            it?.let {
                besinListRecyclerView.visibility = View.VISIBLE
                recylerBesinAdapter.besinListesiniGuncelle(it)

            }
        })
        viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    besinListRecyclerView.visibility = View.VISIBLE
                    besinListRecyclerView.visibility = View.GONE

                } else {
                    besinHataMesaji.visibility = View.GONE
                }

            }
        })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    besinListRecyclerView.visibility = View.GONE
                    besinHataMesaji.visibility = View.GONE
                    besinYukleniyor.visibility = View.VISIBLE
                } else {
                    besinYukleniyor.visibility = View.GONE

                }
            }
        })
    }

}