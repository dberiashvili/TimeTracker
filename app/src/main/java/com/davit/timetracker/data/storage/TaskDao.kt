package com.davit.timetracker.data.storage

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(taskEntity: TaskEntity): Completable

    @Query("SELECT * FROM saved_trackers ORDER BY task")
    fun getTasks(): Observable<List<TaskEntity>>

    @Update
    fun updateTask(taskEntity: TaskEntity): Completable
}