package com.exam.peoplecloud.ui.main

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exam.peoplecloud.databinding.JobFragmentBinding
import com.exam.peoplecloud.models.JobP
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class JobFragment : Fragment() {

    private lateinit var binding: JobFragmentBinding

    lateinit var job: JobP
    private var colorId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = JobFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*(activity as AppCompatActivity?)!!.supportActionBar?.let {
            it.setBackgroundDrawable(resources.getDrawable(R.drawable.backpeoplecloud))
            it.show()
        }*/
        loadUi()
    }

    private fun loadUi() {
        arguments?.let {
            job = it.getSerializable("job") as JobP
            colorId = it.getInt("color")
        }
        binding.tvTitle.text = job.title
        binding.tvCompany.text = job.company
        binding.tvAddress.text = "${job.city}, ${job.state}, ${job.country}"
        binding.tvIcon.text = job.company!!.substring(0, 1)
        (binding.tvIcon.background as GradientDrawable).setColor(colorId)
        binding.tvDate.text = job.date
        val text = job.description!!.replace("\n", "<br>")
        binding.tvDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(text)
        }


    }


}