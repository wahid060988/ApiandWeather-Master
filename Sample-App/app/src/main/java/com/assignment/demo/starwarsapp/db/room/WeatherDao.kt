package com.assignment.demo.starwarsapp.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel

@Dao
interface starwarsDao {

    @Query("SELECT * FROM starwarsTable ORDER BY timestamp DESC LIMIT 10")
    suspend fun fetchSearchData(): List<starwarsRoomDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomDataModel: starwarsRoomDataModel)
}