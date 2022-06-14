package com.example.usuarios.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
@Entity(tableName = "user_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var numberOne: Int,
    var numberTwo: Int,
    var numberThree: Int,
    var numberFour: Int,
    var numberFive: Int,
    var numberSix: Int,
    var date: String
): Parcelable, Serializable