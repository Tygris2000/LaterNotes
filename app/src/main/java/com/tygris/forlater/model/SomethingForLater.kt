package com.tygris.forlater.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "later_table")
data class SomethingForLater(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "title")
    var task : String = " ",
    @ColumnInfo(name = "alarm")
    val alarm : Date? = Date()

)
