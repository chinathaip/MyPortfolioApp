package com.example.myportfolioapp.domain

import com.example.myportfolioapp.data.Info

interface GetInfoRepository {

    suspend fun getInfo() : Info
}