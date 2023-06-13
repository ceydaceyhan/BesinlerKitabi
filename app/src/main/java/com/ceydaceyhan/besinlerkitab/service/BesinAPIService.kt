package com.ceydaceyhan.besinlerkitab.service

import com.ceydaceyhan.besinlerkitab.model.Besin
import com.example.besinlerkicom.ceydaceyhan.besinlerkitab.service.BesinAPI
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class BesinAPIService {
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json


    private val BASE_URL ="https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(BesinAPI::class.java)
    fun getData(): Single<List<Besin>>{
        return api.getBesin()

    }
}