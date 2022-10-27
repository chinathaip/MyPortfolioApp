package com.example.myportfolioapp.domain

import com.example.myportfolioapp.data.InfoGroup

interface GetInfoRepository {

    fun getInfo() : List<InfoGroup>
}