package com.assignment.demo.starwarsapp.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.demo.starwarsapp.db.model.starwarsRoomDataModel

@Database(entities = [starwarsRoomDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun starwarsDao(): starwarsDao

}