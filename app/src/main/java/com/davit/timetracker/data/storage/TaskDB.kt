package com.davit.timetracker.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDB : RoomDatabase() {
    abstract fun TaskDao(): TaskDao
}