package com.assignment.demo.starwarsapp.datamodel.search

import com.google.gson.annotations.SerializedName

data class Search_api (

	@SerializedName("result") val result : List<Result>
)