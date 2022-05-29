package com.assignment.demo.sampleapp.db.room

import com.assignment.demo.sampleapp.db.model.UserRoomDataModel

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

  override suspend fun fetchAllData(): List<UserRoomDataModel> = appDatabase.userDao().fetchAllData()

  override suspend fun insert(userRoomDataModel: UserRoomDataModel) = appDatabase.userDao().insert(userRoomDataModel)

  override suspend fun insert(userRoomDataModels: ArrayList<UserRoomDataModel>) = appDatabase.userDao().insertAll(userRoomDataModels)

  override suspend fun fetchSearchData(name : String): List<UserRoomDataModel> = appDatabase.userDao().fetchSearchData(name)

}