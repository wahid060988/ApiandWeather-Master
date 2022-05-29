package com.assignment.demo.sampleapp.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.demo.sampleapp.datamodel.peoples.Dob
import com.assignment.demo.sampleapp.datamodel.peoples.Login
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "UserTable")
data class UserRoomDataModel(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "login") val login: String?,
    @ColumnInfo(name = "dob") val dob: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "cell") val cell: String?,
    @ColumnInfo(name = "nat") val nat: String?

) : Serializable