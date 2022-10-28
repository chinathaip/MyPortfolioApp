package com.example.myportfolioapp.ui.viewHolder

import com.example.myportfolioapp.databinding.ViewHolderDescriptionSectionBinding
import com.example.myportfolioapp.ui.model.MyInfoModel

class DescriptionViewHolder(val binding: ViewHolderDescriptionSectionBinding) :
    MyInfoViewHolder<MyInfoModel.Description>(binding.root) {

    override fun bindData(data: MyInfoModel.Description) {
        binding.descriptionTextView.text = data.description
    }

}