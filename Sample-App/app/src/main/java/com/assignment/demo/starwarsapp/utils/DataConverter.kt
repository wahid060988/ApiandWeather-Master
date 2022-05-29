package com.assignment.demo.starwarsapp.utils

import com.assignment.demo.starwarsapp.datamodel.peoples.PeopleResponseModel
import com.assignment.demo.starwarsapp.datamodel.peoples.Results
import com.assignment.demo.starwarsapp.datamodel.search.Result
import javax.inject.Inject

class DataConverter @Inject constructor() {
    fun convertToSearchModelList(searchApiResponseData: PeopleResponseModel?): ArrayList<Results> {
        if (searchApiResponseData != null && !searchApiResponseData.results.isNullOrEmpty()) {
            return searchApiResponseData.results as ArrayList
        }

        return ArrayList()
    }

    /*fun convertToDbModel(result: Results): starwarsRoomDataModel {

        return starwarsRoomDataModel(
            prepareDatabaseIdFromResult(result),
            result.areaName[0].value,
            result.country[0].value,
            result.region[0].value,
            result.latitude.toString(),
            result.longitude.toString(),
            result.population.toString(),
            result.starwarsUrl[0].value,
            System.currentTimeMillis()
        )
    }*/

    fun prepareDatabaseIdFromResult(result: Result): String {
        return result.areaName[0].value + ", " + result.region[0].value
    }

    fun prepareDisplayIdFromResult(result: Results): String {
        return result.name + ", " + getIdFromResult(result)
    }

    fun getIdFromResult(result: Results): String {
        val temp = "https://swapi.dev/api/people/"
        return result.url.replace(temp, "").replace("/", "")
    }

}