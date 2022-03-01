package com.davit.timetracker.domain.repo

import com.davit.timetracker.domain.models.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun saveTask(task: Task): Completable
    fun getTasks(): Observable<List<Task>>
    fun updateTask(task: Task): Completable
}