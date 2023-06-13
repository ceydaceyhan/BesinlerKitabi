package com.example.besinlerkicom.ceydaceyhan.besinlerkitab.service

import com.ceydaceyhan.besinlerkitab.model.Besin
import io.reactivex.Single
import retrofit2.http.GET

interface BesinAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json


    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin():Single<List<Besin>>
}