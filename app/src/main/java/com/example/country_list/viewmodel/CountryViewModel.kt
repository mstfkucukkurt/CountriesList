package com.example.country_list.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.country_list.model.Country
import com.example.country_list.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application)  {

    val countriesLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countriesLiveData.value = country
        }
    }

}