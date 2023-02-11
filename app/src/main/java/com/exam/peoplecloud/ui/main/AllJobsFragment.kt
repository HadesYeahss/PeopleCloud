package com.exam.peoplecloud.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.exam.peoplecloud.databinding.AllJobsFragmentBinding
import com.exam.peoplecloud.ui.main.adapters.JonAdapter
import com.exam.peoplecloud.ui.main.viewmodel.AllJobsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllJobsFragment : Fragment() {

    private lateinit var binding: AllJobsFragmentBinding
    private val viewModel: AllJobsViewModel by viewModels()
    private lateinit var indexAdapterUpper: JonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllJobsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        initMembers()
    }

    private fun initMembers() {
        indexAdapterUpper = JonAdapter()
        binding.rvUpper.adapter = indexAdapterUpper
        binding.tvRetry.setOnClickListener {
            showError(View.GONE)
            viewModel.getIndex()
        }
    }

    private fun attachObservers() {
        viewModel.getIndex()
        viewModel.indexListUpper.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                indexAdapterUpper.submitList(it)
            }

            indexAdapterUpper.notifyDataSetChanged()
        }
        viewModel.loader.observe(viewLifecycleOwner) {
            loaderShow(it)
        }
        viewModel.haveError.observe(viewLifecycleOwner) {
            showError(it)
        }
    }

    private fun loaderShow(flag: Int) {
        binding.progressIndex.visibility = flag
    }

    private fun showError(flag: Int) {
        binding.imError.visibility = flag
        binding.tvError.visibility = flag
        binding.tvRetry.visibility = flag
    }
}