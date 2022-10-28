package com.example.myportfolioapp.data

import com.google.gson.annotations.SerializedName

data class InfoGroup(
    @SerializedName("groupName")
    val groupName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageUrl")
    val imageUrl: String?
)
