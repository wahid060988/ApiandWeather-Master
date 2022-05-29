package com.assignment.demo.sampleapp.repository

import com.assignment.demo.sampleapp.api_service.SampleAppApiRequest
import com.assignment.demo.sampleapp.constants.AppConstants
import com.assignment.demo.sampleapp.datamodel.peoples.PeopleResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.RandomUserResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.Results
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiRequest: SampleAppApiRequest) {

    fun search(query: String): Single<RandomUserResponseModel?>? {
        return apiRequest.search(query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }

    fun details(query: String): Single<Results?>? {
        return apiRequest.details(query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }

    fun listItemDetails(query: String): Single<Results?>? {
        return apiRequest.listItemDetails(query)
    }

    fun starList(): Single<PeopleResponseModel>? {
        return apiRequest.starList()
    }

    fun randomUserList(): Single<RandomUserResponseModel>? {
        return apiRequest.randomUserList()
    }


    fun loadMore(url: String?): Single<PeopleResponseModel>? {
        return apiRequest.loadMore(url)
    }
}