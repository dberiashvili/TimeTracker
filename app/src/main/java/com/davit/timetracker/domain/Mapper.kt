package com.davit.timetracker.domain

import com.davit.timetracker.data.storage.TaskEntity
import com.davit.timetracker.domain.models.Task

fun Task.toEntity(): TaskEntity{
    return TaskEntity(id, task, description, time)
}

fun TaskEntity.toDomain(): Task {
    return  Task(id, task, description, time)
}