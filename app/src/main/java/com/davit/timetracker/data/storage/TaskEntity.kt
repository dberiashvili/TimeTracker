package com.davit.timetracker.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_trackers")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    val description: String,
    val time: String
)