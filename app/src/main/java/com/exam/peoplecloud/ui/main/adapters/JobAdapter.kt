package com.exam.peoplecloud.ui.main.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exam.peoplecloud.R
import com.exam.peoplecloud.databinding.SingleItemBinding
import com.exam.peoplecloud.models.JobP
import java.util.*


class JonAdapter :
    ListAdapter<JobP, JonAdapter.ViewHolder>(IndexComplexDiffCallback) {

    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val current = getItem(position)

            binding.tvTitle.text = current.title
            binding.tvCompany.text = current.company
            binding.tvAddress.text = "${current.city}, ${current.state}, ${current.country}"
            binding.tvIcon.text = current.company!!.substring(0, 1)
            val mRandom = Random()
            val color: Int =
                Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256))
            (binding.tvIcon.background as GradientDrawable).setColor(color)
            binding.root.setOnClickListener {
                current?.let { job ->
                    navigateToData(job, it, color)
                }
            }
        }
    }

    private fun navigateToData(job: JobP, view: View, color: Int) {
        val bundle = bundleOf("job" to job)
        bundle.putInt("color", color)
        view.findNavController().navigate(R.id.action_allJobsFragment_to_jobFragment, bundle)
    }
}

object IndexComplexDiffCallback : DiffUtil.ItemCallback<JobP>() {
    override fun areItemsTheSame(oldItem: JobP, newItem: JobP): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: JobP, newItem: JobP): Boolean {
        return oldItem.referencenumber == newItem.referencenumber
    }
}