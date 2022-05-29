package com.assignment.demo.sampleapp.datamodel.peoples

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RandomResults(
  @SerializedName("gender") var gender: String,
  @SerializedName("name") var name: Name = Name(),
  @SerializedName("location") var location: Location? = Location(),
  @SerializedName("email") var email: String? = null,
  @SerializedName("login") var login: Login? = Login(),
  @SerializedName("dob") var dob: Dob? = Dob(),
  @SerializedName("phone") var phone: String? = null,
  @SerializedName("cell") var cell: String? = null,
  @SerializedName("nat") var nat: String? = null
) : Serializable