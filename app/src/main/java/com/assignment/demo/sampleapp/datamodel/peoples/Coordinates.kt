package com.assignment.demo.sampleapp.datamodel.peoples

import com.google.gson.annotations.SerializedName

data class Coordinates (

    @SerializedName("latitude"  ) var latitude  : String? = null,
    @SerializedName("longitude" ) var longitude : String? = null

)