package com.example.myportfolioapp.data

import com.example.myportfolioapp.domain.GetInfoRepository

class GetInfoRepositoryImpl(private val dataSource: MyInfoDataSource) : GetInfoRepository {
    override suspend fun getInfo(): Info {
        return dataSource.getMyInfo()
    }
}