package com.example.myportfolioapp.ui.model

sealed class MyInfoModel(val type: Int) {


    data class Section(val sectionName: String) : MyInfoModel(MyInfoModelType.GROUP_SECTION_TYPE)

    data class Description(val description: String) :
        MyInfoModel(MyInfoModelType.DESCRIPTION_SECTION_TYPE)

    object ProfilePicture : MyInfoModel(MyInfoModelType.IMAGE_SECTION_TYPE)
}
