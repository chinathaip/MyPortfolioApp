package com.example.myportfolioapp.di

import com.example.myportfolioapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val module = module {
    viewModel { MainViewModel(get()) }
}