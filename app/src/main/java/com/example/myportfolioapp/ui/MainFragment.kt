package com.example.myportfolioapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myportfolioapp.databinding.FragmentMainBinding
import com.example.myportfolioapp.ui.adapter.MyInfoAdapter
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
        }
    }

    private fun observeViewModel() {
        viewModel.listOfInfo.observe(viewLifecycleOwner) { listOfInfo ->
            infoAdapter.info.addAll(listOfInfo)
        }
    }

}