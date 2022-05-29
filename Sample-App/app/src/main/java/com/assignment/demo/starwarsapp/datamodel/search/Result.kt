package com.assignment.demo.starwarsapp.datamodel.search

import com.google.gson.annotations.SerializedName

data class Result (
    @SerializedName("areaName") val areaName : List<AreaName>,
    @SerializedName("country") val country : List<Country>,
    @SerializedName("region") val region : List<Region>,
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double,
    @SerializedName("population") val population : Int,
    @SerializedName("starwarsUrl") val starwarsUrl : List<starwarsUrl>
) : java.io.Serializable