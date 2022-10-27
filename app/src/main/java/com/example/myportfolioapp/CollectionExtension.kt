package com.example.myportfolioapp

fun <E : Any, T : Collection<E>> T?.whenNotNullAndEmpty(func: (T) -> Unit) {
    if (!this.isNullOrEmpty()) {
        func.invoke(this)
    }
}