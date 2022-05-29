package com.assignment.demo.starwarsapp.datamodel.city_wheather

import com.google.gson.annotations.SerializedName

data class Request (

	@SerializedName("type") val type : String,
	@SerializedName("query") val query : String
)