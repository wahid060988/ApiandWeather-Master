package com.assignment.demo.sampleapp.datamodel.peoples

import com.google.gson.annotations.SerializedName

data class RandomUserResponseModel (

    @SerializedName("results" ) var results : ArrayList<RandomResults> = arrayListOf(),
)