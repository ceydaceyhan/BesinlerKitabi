package com.ceydaceyhan.besinlerkitab.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceydaceyhan.besinlerkitab.model.Besin
import com.ceydaceyhan.besinlerkitab.service.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayiViewModelTwo(application: Application) : BaseViewModel(application) {
    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl (uudi : Int){
        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uudi)
            besinLiveData.value=besin
        }
    }
}