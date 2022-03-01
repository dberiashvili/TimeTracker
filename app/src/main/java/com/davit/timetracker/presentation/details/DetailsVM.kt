package com.davit.timetracker.presentation.details

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.davit.timetracker.domain.models.Task
import com.davit.timetracker.domain.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(private val repository: Repository) : ViewModel() {
    fun checkInputs(nameET: EditText, descriptionET: EditText, timeET: EditText): Boolean {
        return nameET.text.isNotEmpty() && descriptionET.text.isNotEmpty() && timeET.text.isNotEmpty()
    }

    fun saveTask(task: Task): Completable = repository.saveTask(task).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}