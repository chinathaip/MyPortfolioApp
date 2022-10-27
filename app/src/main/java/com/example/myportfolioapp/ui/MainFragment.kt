package com.example.myportfolioapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myportfolioapp.R
import com.example.myportfolioapp.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel> { parametersOf("HAHA") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.inflate(LayoutInflater.from(this.context))
        setUpView()
        observeViewModel()
    }

    private fun setUpView() {

    }

    private fun observeViewModel() {
        viewModel.testDi.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        }
    }

}