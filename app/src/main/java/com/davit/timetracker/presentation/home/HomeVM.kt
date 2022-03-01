package com.davit.timetracker.presentation.home

import androidx.lifecycle.ViewModel
import com.davit.timetracker.domain.models.Task
import com.davit.timetracker.domain.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getTasks(): Observable<List<Task>> = repository.getTasks().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun startCountDown(time: Int): Observable<Int> {
        return Observable.zip(
            Observable.interval(1000L, TimeUnit.MILLISECONDS),
            Observable.range(0, time)
        ) { _, t ->
            time - t - 1
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateTask(task: Task): Completable =
        repository.updateTask(task).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}