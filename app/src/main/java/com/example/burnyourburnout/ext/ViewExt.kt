package com.example.burnyourburnout.ext

import android.view.View
import androidx.annotation.Px

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}