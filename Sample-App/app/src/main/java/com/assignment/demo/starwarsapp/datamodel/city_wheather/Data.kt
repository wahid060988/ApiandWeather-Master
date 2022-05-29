package com.assignment.demo.starwarsapp.datamodel.city_wheather

import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("request") val request : List<Request>,
	@SerializedName("current_condition") val current_condition : List<Current_condition>
)