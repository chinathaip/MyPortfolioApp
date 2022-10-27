package com.example.myportfolioapp.ui.viewHolder

import com.example.myportfolioapp.databinding.ViewHolderGroupSectionBinding
import com.example.myportfolioapp.ui.model.MyInfoModel

class GroupSectionViewHolder(private val binding: ViewHolderGroupSectionBinding) :
    MyInfoViewHolder<MyInfoModel.Section>(binding.root) {

    override fun bindData(data: MyInfoModel.Section) {
        binding.groupSectionTextView.text = data.sectionName
    }

}