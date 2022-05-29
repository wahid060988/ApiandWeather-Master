package com.assignment.demo.starwarsapp.datamodel.city_wheather

import com.google.gson.annotations.SerializedName

data class starwarsApiResponseModel(
    @SerializedName("data") val data: Data
)