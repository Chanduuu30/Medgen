package com.example.medgen.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.medgen.History


@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(history: History)

    @Query("SELECT * FROM history_table ORDER BY timestamp DESC")
    suspend fun getAllHistory(): List<History>
}
