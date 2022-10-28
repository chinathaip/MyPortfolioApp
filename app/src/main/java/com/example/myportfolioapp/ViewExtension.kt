package com.example.myportfolioapp

import android.view.View

fun View.visibleIf(condition: Boolean) {
    if (condition) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.visibleIf(condition: Boolean, whenVisible: (View) -> Unit) {
    if (condition) {
        this.visibility = View.VISIBLE
        whenVisible.invoke(this)
    } else {
        this.visibility = View.GONE
    }
}