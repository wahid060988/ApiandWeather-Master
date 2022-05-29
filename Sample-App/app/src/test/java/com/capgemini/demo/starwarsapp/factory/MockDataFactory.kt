package com.assignment.demo.starwarsapp.factory

import com.assignment.demo.starwarsapp.datamodel.search.*
import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel

class MockDataFactory {

    companion object {

        fun getSearchApiResponseModel(): SearchApiResponseModel {
            val results: ArrayList<Result> = ArrayList()
            results.add(getResultModel())
            val searchApi = Search_api(results)
            return SearchApiResponseModel(searchApi);
        }

        fun getstarwarsRoomDataModel(): starwarsRoomDataModel {
            val responseModel = starwarsRoomDataModel(
                "area-region-country", "area", "country",
                "region", "11.11", "22.22", "10000", "https://test.url", 1000L
            )
            return responseModel;
        }

        fun getstarwarsRoomDataModel(id: String): starwarsRoomDataModel {
            val responseModel = starwarsRoomDataModel(
                id, "area", "country",
                "region", "11.11", "22.22", "10000", "https://test.url", 1000L
            )
            return responseModel;
        }

        fun getResultModel(): Result {
            val area = ArrayList<AreaName>()
            area.add(AreaName("area"))

            val country = ArrayList<Country>()
            country.add(Country("country"))

            val region = ArrayList<Region>()
            region.add(Region("region"))

            val starwarsUrl = ArrayList<starwarsUrl>()
            starwarsUrl.add(starwarsUrl("https://test.url"))

            return Result(area, country, region, 11.11, 22.22, 10000, starwarsUrl);
        }


    }
}