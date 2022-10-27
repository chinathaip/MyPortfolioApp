package com.example.myportfolioapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val lol: String
) : ViewModel() {

    val testDi = MutableLiveData<String>()

    fun printLol() {
        testDi.value = lol
    }
}