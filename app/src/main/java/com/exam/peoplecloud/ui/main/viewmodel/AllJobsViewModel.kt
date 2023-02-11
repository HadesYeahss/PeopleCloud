package com.exam.peoplecloud.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.*
import com.exam.peoplecloud.models.JobP
import com.exam.peoplecloud.remote.responses.ResponseResult
import com.exam.peoplecloud.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AllJobsViewModel @Inject constructor(
    private val indexRepository: JobsRepository
) : ViewModel() {

    var indexListUpper = MutableLiveData<List<JobP>>()
    var loader = MutableLiveData<Int>()
    var haveError = MutableLiveData<Int>()

    fun getIndex() = viewModelScope.launch {
        loader.postValue(View.VISIBLE)
        indexRepository.getJobs().collect {
            loader.postValue(View.GONE)
            when (it) {
                is ResponseResult.Success -> {
                    it.data.let { list ->
                        indexListUpper.postValue(list.jobP)
                    }
                }
                is ResponseResult.Failure -> {
                    val error = it.error
                    haveError.postValue(View.VISIBLE)
                }
            }
        }
    }
}