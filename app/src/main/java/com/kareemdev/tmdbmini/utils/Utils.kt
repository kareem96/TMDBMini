package com.kareemdev.tmdbmini.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object Utils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(context: Context, view: View, message: String?, runnable: Runnable) {
        val safeMessage = message ?: "Unknown Error"
        Snackbar.make(view, safeMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                runnable.run()
            }
            .apply {
                anchorView = view
            }.show()
    }
}