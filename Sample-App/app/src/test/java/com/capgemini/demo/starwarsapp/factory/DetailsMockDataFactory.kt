package com.assignment.demo.starwarsapp.factory

import com.assignment.demo.starwarsapp.datamodel.city_wheather.*
import com.assignment.demo.starwarsapp.datamodel.search.*

class DetailsMockDataFactory {

    companion object {

        fun getstarwarsApiResponseModel(): starwarsApiResponseModel {
            val request: ArrayList<Request> = ArrayList()
            request.add(getRequest())

            val currentCondition: ArrayList<Current_condition> = ArrayList()
            currentCondition.add(getCurrentCondition())

            val data = Data(request, currentCondition)
            return starwarsApiResponseModel(data);
        }

        private fun getRequest(): Request {
            return Request("city", "Pune, India");
        }

        fun getCurrentCondition(): Current_condition {
            val starwarsDesc: ArrayList<starwarsDesc> = ArrayList()
            starwarsDesc.add(starwarsDesc("Cloudy"))

            val starwarsIconUrl: ArrayList<starwarsIconUrl> = ArrayList()
            starwarsIconUrl.add(starwarsIconUrl("https://Cloudy.url"))

            return Current_condition(
                temp_C = 37,
                temp_F = 37,
                starwarsDesc = starwarsDesc,
                starwarsIconUrl = starwarsIconUrl,
                humidity = 60
            );
        }


    }
}