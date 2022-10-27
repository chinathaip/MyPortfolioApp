package com.example.myportfolioapp.ui.model

import com.example.myportfolioapp.data.InfoGroup

object InfoGroupToMyInfoModelMapper {
    fun map(infoGroup: List<InfoGroup>): List<MyInfoModel> {
        val newList = mutableListOf<MyInfoModel>()
        infoGroup.forEach { data ->
            newList.add(MyInfoModel.Section(data.groupName))
            newList.add(MyInfoModel.Description(data.description))
        }
        return newList
    }
}