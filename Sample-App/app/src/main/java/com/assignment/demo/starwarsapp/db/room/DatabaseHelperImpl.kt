package com.assignment.demo.starwarsapp.db.room

import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun fetchSearchData(): List<starwarsRoomDataModel> = appDatabase.starwarsDao().fetchSearchData()

    override suspend fun insert(starwarsRoomDataModel: starwarsRoomDataModel) = appDatabase.starwarsDao().insert(starwarsRoomDataModel)


    override suspend fun fetchSearchData(): List<starwarsRoomDataModel> = appDatabase.starwarsDao().fetchSearchData()

    override suspend fun insertRandomUserData(starwarsRoomDataModel: RandomUserData) = appDatabase.starwarsDao().insert(starwarsRoomDataModel)

}