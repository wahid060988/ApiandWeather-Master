package com.assignment.demo.starwarsapp.db.room

import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel

interface DatabaseHelper {

    suspend fun fetchSearchData(): List<starwarsRoomDataModel>

    suspend fun insert(starwarsRoomDataModel: starwarsRoomDataModel)

}