package com.example.myportfolioapp.ui.viewHolder

import coil.api.load
import com.example.myportfolioapp.databinding.ViewHolderImageSectionBinding
import com.example.myportfolioapp.ui.model.MyInfoModel

class ImageSectionViewHolder(val binding: ViewHolderImageSectionBinding) :
    MyInfoViewHolder<MyInfoModel.Image>(binding.root) {

    override fun bindData(data: MyInfoModel.Image, position: Int) {
        binding.imageView.load(data.imageUrl)
    }
}