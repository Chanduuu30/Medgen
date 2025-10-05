package com.example.medgen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,          // e.g. "scan" or "symptom"
    val inputText: String,     // original scanned text or symptom description
    val resultText: String,    // AI/translated output
    val timestamp: Long        // when it was created
)
