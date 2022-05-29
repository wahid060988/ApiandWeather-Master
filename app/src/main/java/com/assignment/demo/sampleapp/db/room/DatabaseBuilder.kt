package com.assignment.demo.sampleapp.db.room

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

  private var INSTANCE: AppDatabase? = null

  fun getInstance(context: Context?): AppDatabase {
    if (INSTANCE == null) {
      synchronized(AppDatabase::class) {
        INSTANCE = context?.let { buildRoomDB(it) }
      }
    }
    return INSTANCE!!
  }

  private fun buildRoomDB(context: Context) =
    Room.databaseBuilder(
      context.applicationContext,
      AppDatabase::class.java,
      "com.assignment.demo.sampleapp"
    ).build()

}