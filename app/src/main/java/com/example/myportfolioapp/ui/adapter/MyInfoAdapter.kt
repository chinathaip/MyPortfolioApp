package com.example.myportfolioapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolioapp.databinding.ViewHolderDescriptionSectionBinding
import com.example.myportfolioapp.databinding.ViewHolderGroupSectionBinding
import com.example.myportfolioapp.databinding.ViewHolderImageSectionBinding
import com.example.myportfolioapp.ui.model.MyInfoModel
import com.example.myportfolioapp.ui.model.MyInfoModelType
import com.example.myportfolioapp.ui.viewHolder.DescriptionViewHolder
import com.example.myportfolioapp.ui.viewHolder.GroupSectionViewHolder
import com.example.myportfolioapp.ui.viewHolder.ImageSectionViewHolder
import com.example.myportfolioapp.ui.viewHolder.MyInfoViewHolder

class MyInfoAdapter : RecyclerView.Adapter<MyInfoViewHolder<*>>() {

    val info = mutableListOf<MyInfoModel>()

    override fun getItemViewType(position: Int): Int = info[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInfoViewHolder<*> {
        return when (viewType) {
            MyInfoModelType.GROUP_SECTION_TYPE -> GroupSectionViewHolder(
                ViewHolderGroupSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            MyInfoModelType.IMAGE_SECTION_TYPE -> ImageSectionViewHolder(
                ViewHolderImageSectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> DescriptionViewHolder(
                ViewHolderDescriptionSectionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: MyInfoViewHolder<*>, position: Int) {
        (holder as MyInfoViewHolder<MyInfoModel>).bindData(info[position], position)
    }

    override fun getItemCount(): Int = info.size
}