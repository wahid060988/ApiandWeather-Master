package com.assignment.demo.sampleapp.db.room

import com.assignment.demo.sampleapp.db.model.UserRoomDataModel

interface DatabaseHelper {

    suspend fun fetchAllData(): List<UserRoomDataModel>

    suspend fun insert(userRoomDataModel: UserRoomDataModel)

    suspend fun insert(userRoomDataModels: ArrayList<UserRoomDataModel>)

    suspend fun fetchSearchData(name: String): List<UserRoomDataModel>


}