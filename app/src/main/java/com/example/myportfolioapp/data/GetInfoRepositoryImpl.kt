package com.example.myportfolioapp.data

import com.example.myportfolioapp.domain.GetInfoRepository

class GetInfoRepositoryImpl : GetInfoRepository {
    override fun getInfo(): List<InfoGroup> {
        return listOf(
            InfoGroup("About Me", "My name is Chinathai Panditya"),
            InfoGroup("Education", "IT, Stamford International University"),
            InfoGroup("Experience", "Software Engineer, Android (Internship) @LINE MAN Wongnai")
        )
    }
}