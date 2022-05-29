package com.assignment.demo.starwarsapp.datamodel.search

import com.google.gson.annotations.SerializedName

data class SearchApiResponseModel (
	@SerializedName("search_api") val search_api : Search_api
)