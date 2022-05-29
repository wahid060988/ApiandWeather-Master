package com.assignment.demo.starwarsapp.details.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.demo.starwarsapp.base.BaseApiResponseModel
import com.assignment.demo.starwarsapp.datamodel.city_wheather.Current_condition
import com.assignment.demo.starwarsapp.datamodel.city_wheather.starwarsApiResponseModel
import com.assignment.demo.starwarsapp.datamodel.peoples.Results
import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel
import com.assignment.demo.starwarsapp.home.ApiRepository
import com.assignment.demo.starwarsapp.utils.LiveDataUtils
import io.reactivex.rxjava3.core.Single

class DetailsViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun getData(arguments: Bundle): String {
        return arguments.getSerializable("selectedId") as String
    }

    fun getScreen(arguments: Bundle): String {
        return arguments.getString("Screen",null) as String
    }


    fun getGeoLocationText(roomDataModel: starwarsRoomDataModel?): CharSequence? {
        return roomDataModel?.latitude.toString() + "," + roomDataModel?.longitude.toString()
    }

    private val peopleResultsMutableLiveData: MutableLiveData<BaseApiResponseModel<Results>> =
        MutableLiveData()

    fun getPeopleResultsMutableLiveData(): MutableLiveData<BaseApiResponseModel<Results>> {
        return peopleResultsMutableLiveData;
    }

    fun fetchPeopleResultsResponseLiveData(searchQuery: String) {
        val apiResponseModelSingle: Single<Results?>? =
            apiRepository.details(searchQuery);
        LiveDataUtils.updateStatus(peopleResultsMutableLiveData, apiResponseModelSingle)
    }

    fun fetchListItemPeopleResultsResponseLiveData(searchQuery: String) {
        val apiResponseModelSingle: Single<Results?>? =
            apiRepository.listitemdetails(searchQuery);
        LiveDataUtils.updateStatus(peopleResultsMutableLiveData, apiResponseModelSingle)
    }


    fun getTemperatureText(currentCondition: Current_condition): CharSequence? {
        return currentCondition.temp_C.toString() + " C"
    }
}