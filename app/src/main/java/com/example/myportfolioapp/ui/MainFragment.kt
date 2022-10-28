package com.example.myportfolioapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolioapp.databinding.FragmentMainBinding
import com.example.myportfolioapp.ui.adapter.MyInfoAdapter
import com.example.myportfolioapp.ui.viewHolder.GroupSectionViewHolder
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val infoAdapter = MyInfoAdapter()
    private var isTabClicked = false
    private lateinit var smoothScroller: LinearSmoothScroller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(LayoutInflater.from(this.context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeViewModel()
    }

    private fun setUpView() {
        smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        binding.myInfoRecyclerView.apply {
            adapter = infoAdapter
            layoutManager = LinearLayoutManager(this.context)
            addOnScrollListener(OnScrollListener())
        }
        binding.tabLayout.addOnTabSelectedListener(OnTabActionListener())
    }

    private fun observeViewModel() {
        viewModel.listOfInfo.observe(viewLifecycleOwner) { listOfInfo ->
            infoAdapter.info.addAll(listOfInfo)
            infoAdapter.notifyItemRangeInserted(0, listOfInfo.size)
        }
        viewModel.tabGroupName.observe(viewLifecycleOwner) { groupNames ->
            addTabGroups(groupNames)
        }
        viewModel.selectTab.observe(viewLifecycleOwner) { tabPosition ->
            binding.tabLayout.getTabAt(tabPosition)?.select()
        }
        viewModel.scrollToGroup.observe(viewLifecycleOwner) { targetPosition ->
            smoothScroller.targetPosition = targetPosition
            binding.myInfoRecyclerView.layoutManager?.startSmoothScroll(smoothScroller)
        }
    }

    private fun addTabGroups(tabGroupName: List<String>) {
        binding.tabLayout.apply {
            tabGroupName.forEach { groupName ->
                val newTab = newTab().apply {
                    this.text = groupName
                    this.view.setOnClickListener { isTabClicked = true }
                }
                addTab(newTab)
            }
        }
    }

    inner class OnScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                isTabClicked = false
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!isTabClicked) {
                (binding.myInfoRecyclerView.layoutManager as LinearLayoutManager).apply {
                    val topPosition = findFirstCompletelyVisibleItemPosition()
                    val topViewHolder = recyclerView.findViewHolderForAdapterPosition(topPosition)
                    if (topViewHolder is GroupSectionViewHolder) {
                        viewModel.handleTabSelection(
                            topViewHolder.binding.groupSectionTextView.text.toString(),
                            isScrollDown = dy > 0
                        )
                    }
                }
            }
        }
    }

    inner class OnTabActionListener : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (isTabClicked) {
                viewModel.handleScrollToGroup(tab?.text.toString())
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            if (isTabClicked) {
                viewModel.handleScrollToGroup(tab?.text.toString())
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
    }
}