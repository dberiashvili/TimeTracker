package com.davit.timetracker.presentation.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

val bag = CompositeDisposable()
fun Context.share(time: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, time)
        type = "text/plain"
    }
    startActivity(intent)
}

fun Fragment.showDialog(@StringRes title: Int, time: String): AlertDialog =
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(time)
        .show()

fun Disposable.untilDestroy() {
    bag.add(this)
}
