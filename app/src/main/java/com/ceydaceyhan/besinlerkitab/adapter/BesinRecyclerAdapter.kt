package com.ceydaceyhan.besinlerkitab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ceydaceyhan.besinlerkitab.R
import com.ceydaceyhan.besinlerkitab.adapter.BesinRecyclerAdapter.*
import com.ceydaceyhan.besinlerkitab.databinding.BesinRecyclerRowBinding
import com.ceydaceyhan.besinlerkitab.fragment.BesinListesiFragmentDirections
import com.ceydaceyhan.besinlerkitab.model.Besin
import com.ceydaceyhan.besinlerkitab.util.gorselIndir
import com.ceydaceyhan.besinlerkitab.util.placeholderYap
import kotlinx.android.synthetic.main.besin_recycler_row.view.*

class BesinRecyclerAdapter(val besinListesi: ArrayList<Besin>) :
    RecyclerView.Adapter<BesinViewHolder>() {
    class BesinViewHolder(itemView: BesinRecyclerRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.besin_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<BesinRecyclerRowBinding>(
            inflater,
            R.layout.besin_recycler_row,
            parent,
            false
        )
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.itemView.besin.text = besinListesi[position].besinIsim
        holder.itemView.kalorisi.text = besinListesi[position].besinKalori

        holder.itemView.setOnClickListener {
            val action =
                BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayFragment(
                    besinListesi[position].uuid
                )
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.gorselIndir(
            besinListesi[position].besinGorsel,
            placeholderYap(holder.itemView.context)
        )


    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun besinListesiniGuncelle(yeniBesinListesi: List<Besin>) {
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }
}