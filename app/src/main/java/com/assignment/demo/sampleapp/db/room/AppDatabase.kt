package com.assignment.demo.sampleapp.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.demo.sampleapp.db.model.UserRoomDataModel

@Database(entities = [UserRoomDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}