package com.example.myportfolioapp.domain

import com.example.myportfolioapp.data.InfoGroup

class GetMyInfoUseCase(private val getInfoRepository: GetInfoRepository) {

    operator fun invoke(): List<InfoGroup> {
        return getInfoRepository.getInfo()
    }
}