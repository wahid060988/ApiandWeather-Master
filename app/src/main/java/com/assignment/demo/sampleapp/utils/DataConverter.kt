package com.assignment.demo.sampleapp.utils

import com.assignment.demo.sampleapp.BuildConfig
import com.assignment.demo.sampleapp.constants.AppConstants
import com.assignment.demo.sampleapp.datamodel.peoples.Dob
import com.assignment.demo.sampleapp.datamodel.peoples.Login
import com.assignment.demo.sampleapp.datamodel.peoples.Name
import com.assignment.demo.sampleapp.datamodel.peoples.PeopleResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.RandomResults
import com.assignment.demo.sampleapp.datamodel.peoples.RandomUserResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.Results
import com.assignment.demo.sampleapp.db.model.UserRoomDataModel
import javax.inject.Inject

class DataConverter @Inject constructor() {
  fun convertToSearchModelList(searchApiResponseData: PeopleResponseModel?): ArrayList<Results> {
    if (searchApiResponseData != null && !searchApiResponseData.results.isNullOrEmpty()) {
      return searchApiResponseData.results as ArrayList
    }
    return ArrayList()
  }

  fun prepareDisplayIdFromResult(result: Results): String {
    return result.name + ", " + getIdFromResult(result)
  }

  fun getIdFromResult(result: Results): String {
    val resultApi = BuildConfig.BASE_URL + AppConstants.PEOPLE_SLASH
    return result.url.replace(resultApi, "").replace("/", "")
  }

  fun transformApiDataToDbData(data: RandomUserResponseModel): ArrayList<UserRoomDataModel> {
    return data.results.transform() as ArrayList<UserRoomDataModel>
  }

  // transforms a single object
  private fun RandomResults.transform(): UserRoomDataModel {
    this.apply {
      return UserRoomDataModel(
        getName(name), gender, email, getLogin(login), getDob(dob), phone, cell, nat
      )
    }
  }

  // transforming lists
  private fun List<RandomResults>.transform(): List<UserRoomDataModel> {
    return this.map {
      it.transform()
    }
  }

  private fun getName(name: Name): String {
    return name.title + " " + name.first + " " + name.last
  }

  private fun getLogin(login: Login?): String? {
    return login?.password
  }

  private fun getDob(dob: Dob?): String? {
    return dob?.date
  }

}