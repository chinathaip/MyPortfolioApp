package com.example.myportfolioapp.ui.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MyInfoViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bindData(data: T)
}