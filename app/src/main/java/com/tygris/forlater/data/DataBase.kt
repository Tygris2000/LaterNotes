package com.tygris.forlater.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tygris.forlater.model.SomethingForLater
@TypeConverters(DbConverter::class)
@Database(entities = [SomethingForLater::class], version = 1 , exportSchema = false)
abstract class DataBase : RoomDatabase(){
   abstract fun dao() : LaterDao
}