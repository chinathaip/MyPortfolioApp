package com.example.myportfolioapp.data

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("info")
    val infoGroup: MutableList<InfoGroup>
)
