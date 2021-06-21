package com.tygris.forlater.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tygris.forlater.model.SomethingForLater

@Dao
interface LaterDao {


    @Query("SELECT * FROM later_table ORDER BY id")
    fun getAll() : LiveData<List<SomethingForLater>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg SomethingForLater: SomethingForLater)

    @Delete
    suspend fun delete(vararg SomethingForLater: SomethingForLater)

    @Query("SELECT * FROM later_table WHERE title LIKE :title")
    fun search(vararg title : String) : LiveData<List<SomethingForLater>>

}