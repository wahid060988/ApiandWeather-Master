package com.assignment.demo.sampleapp.api_service

import com.assignment.demo.sampleapp.datamodel.peoples.PeopleResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.RandomUserResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.Results
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SampleAppApiRequest {

    @GET("results/?")
    fun search(
        @Query("search") queryText: String,
        @Query("format") format: String
    ): Single<RandomUserResponseModel?>?

    @GET("people/{query}/")
    fun details(
        @Path("query") queryText: String,
        @Query("format") format: String
    ): Single<Results?>?

    @GET()
    fun listItemDetails(
        @Url queryText: String,
    ): Single<Results?>?

    @GET("people")
    fun starList(): Single<PeopleResponseModel>

    @GET("?results=10")
    fun randomUserList(): Single<RandomUserResponseModel>

    @GET()
    fun loadMore(@Url url: String?): Single<PeopleResponseModel>

}