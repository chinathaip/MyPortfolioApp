package com.example.myportfolioapp.data

import com.google.gson.Gson
import kotlinx.coroutines.delay

class MyInfoDataSource {
    suspend fun getMyInfo(): Info {
        delay(1000)
        return createMyInfo()
    }

    private fun createMyInfo(): Info {
        val json = """
        {
            "info": [
                {
                  "groupName": "About Me",
                  "description": "My name is Chinathai Panditya. You can call me 'Khing'. I'm a sophomore IT student at Stamford International University. I've found my passion in computer programming since high school when I was taking the IGCSE Computer Science course. In my free time, I usually play video games and watch youtube videos",
                  "imageUrl": "https://media.discordapp.net/attachments/853911770430177305/1035238374237274132/IMG_7599.jpg"
                },
                {
                  "groupName": "Education",
                  "description": "I finished primary school at Pichaya Suksa School, then continued my high school life at Charter International School. Currently, I'm studying at Stamford International University in the faculty of Business and Technology with a concentration track of Software Engineering",
                  "imageUrl": "https://www.stamford.edu/wp-content/uploads/2018/06/Stamford-International-University.jpg"
                },
                {
                  "groupName": "Internship Experience",
                  "description": "On my second year of university, I interned at LINE MAN Wongnai in a Software Engineer, Android role ",
                  "imageUrl": "https://media.discordapp.net/attachments/1028047367909425333/1028047903211671612/IMG_1711.jpg"
                },
                {
                  "groupName": "Projects",
                  "description": "So far I had worked on 3 different android projects: PoS Application during my android development course at university, PromVac - vaccination appointment application which I used as a revision project before starting my internship, and lastly, Wongnai App which I spent the whole 3 months and a half working on during my internship",
                  "imageUrl": "https://www.storybench.org/wp-content/uploads/2015/09/github-octocat.jpg"
                }
            ]
        }
        """
        return Gson().fromJson(json, Info::class.java)
    }
}