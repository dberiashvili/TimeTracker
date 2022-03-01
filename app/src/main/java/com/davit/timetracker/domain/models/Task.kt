package com.davit.timetracker.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id: Int = 0,
    val task: String,
    val description: String,
    val time: String
): Parcelable
