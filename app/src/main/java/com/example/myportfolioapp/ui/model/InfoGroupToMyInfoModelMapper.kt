package com.example.myportfolioapp.ui.model

import com.example.myportfolioapp.data.InfoGroup
import com.example.myportfolioapp.whenNotBlank

object InfoGroupToMyInfoModelMapper {
    fun map(infoGroup: List<InfoGroup>): List<MyInfoModel> {
        val newList = mutableListOf<MyInfoModel>()
        infoGroup.forEach { data ->
            newList.add(MyInfoModel.GroupSection(data.groupName))
            newList.add(MyInfoModel.Description(data.description))
            data.imageUrl?.whenNotBlank { newList.add(MyInfoModel.Image(it)) }
        }
        return newList
    }
}