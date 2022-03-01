package com.davit.timetracker.data.repository

import com.davit.timetracker.data.storage.TaskDB
import com.davit.timetracker.domain.models.Task
import com.davit.timetracker.domain.repo.Repository
import com.davit.timetracker.domain.toDomain
import com.davit.timetracker.domain.toEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private var taskDB: TaskDB
) : Repository {
    override fun saveTask(task: Task): Completable {
        return taskDB.TaskDao().insertTask(task.toEntity())
    }

    override fun getTasks(): Observable<List<Task>> {
        return taskDB.TaskDao().getTasks().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override fun updateTask(task: Task): Completable {
        return taskDB.TaskDao().updateTask(task.toEntity())
    }


}