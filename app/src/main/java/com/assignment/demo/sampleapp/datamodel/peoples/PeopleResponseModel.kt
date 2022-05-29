package com.assignment.demo.sampleapp.datamodel.peoples

import com.google.gson.annotations.SerializedName

data class PeopleResponseModel (

	@SerializedName("count") val count : Int,
	@SerializedName("next") val next : String,
	@SerializedName("previous") val previous : String,
	@SerializedName("results") val results : List<Results>
)