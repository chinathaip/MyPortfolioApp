package com.example.myportfolioapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myportfolioapp.databinding.FragmentMainBinding
import com.example.myportfolioapp.ui.adapter.MyInfoAdapter
import com.example.myportfolioapp.ui.viewHolder.GroupSectionViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val infoAdapter = MyInfoAdapter()

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
        binding.myInfoRecyclerView.apply {
            adapter = infoAdapter
            layoutManager = LinearLayoutManager(this.context)
            addOnScrollListener(OnScrollListener())
        }
    }

    private fun observeViewModel() {
        viewModel.listOfInfo.observe(viewLifecycleOwner) { listOfInfo ->
            infoAdapter.info.addAll(listOfInfo)
        }
        viewModel.tabGroupName.observe(viewLifecycleOwner) { groupNames ->
            addTabGroups(groupNames)
        }
        viewModel.selectTab.observe(viewLifecycleOwner) { tabPosition ->
            tabPosition ?: return@observe
            selectTab(tabPosition)
        }
    }

    private fun addTabGroups(tabGroupName: List<String>) {
        binding.tabLayout.apply {
            tabGroupName.forEach { groupName ->
                val newTab = newTab().apply { this.text = groupName }
                addTab(newTab)
            }
        }
    }

    private fun selectTab(tabPosition: Int) {
        binding.tabLayout.apply {
            getTabAt(tabPosition)?.select()
        }
    }

    inner class OnScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
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