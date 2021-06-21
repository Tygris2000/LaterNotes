package com.tygris.forlater.data

import androidx.room.TypeConverter

import java.util.*

class DbConverter {
    @TypeConverter
    fun fromDate(date : Date?) : Long?{
        return date?.time
    }
    @TypeConverter
    fun toDate(long : Long?) : Date?{
       return long?.let {
           Date(it)
       }
    }
}