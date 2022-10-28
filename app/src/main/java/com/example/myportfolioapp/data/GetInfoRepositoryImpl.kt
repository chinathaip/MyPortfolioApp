package com.example.myportfolioapp.data

import com.example.myportfolioapp.domain.GetInfoRepository

class GetInfoRepositoryImpl : GetInfoRepository {
    override fun getInfo(): List<InfoGroup> {
        return listOf(
            InfoGroup(
                "About Me", """
                My name is Chinathai Panditya. 
                I'm currently a sophomore student at Stamford International University. 
                I've found my passion in computer programming since high school when I was taking IGCSE Computer Science course.
                In my free time, I usually play video games and watch youtube videos
                """.trimIndent()
            ),
            InfoGroup(
                "Education", """
                Pichaya Suksa School
                Charter International School
                Stamford International University
                 """.trimIndent()
            ),
            InfoGroup("Experience", "Software Engineer, Android (Internship) @LINE MAN Wongnai")
        )
    }
}