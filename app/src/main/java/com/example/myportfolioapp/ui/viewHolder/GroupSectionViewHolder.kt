package com.example.myportfolioapp.ui.viewHolder

import com.example.myportfolioapp.databinding.ViewHolderGroupSectionBinding
import com.example.myportfolioapp.ui.model.MyInfoModel
import com.example.myportfolioapp.visibleIf

class GroupSectionViewHolder(val binding: ViewHolderGroupSectionBinding) :
    MyInfoViewHolder<MyInfoModel.GroupSection>(binding.root) {

    override fun bindData(data: MyInfoModel.GroupSection, position: Int) {
        binding.groupSectionTextView.text = data.sectionName
        binding.divider.visibleIf(position > 0)
    }

}