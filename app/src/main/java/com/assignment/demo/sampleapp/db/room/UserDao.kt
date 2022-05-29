package com.assignment.demo.sampleapp.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.demo.sampleapp.db.model.UserRoomDataModel

@Dao
interface UserDao {

  @Query("SELECT * FROM UserTable")
  suspend fun fetchAllData(): List<UserRoomDataModel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(roomDataModel: UserRoomDataModel)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(roomDataModels: List<UserRoomDataModel>)

  @Query("SELECT * FROM UserTable WHERE name LIKE :name")
  suspend fun fetchSearchData(name: String): List<UserRoomDataModel>
}