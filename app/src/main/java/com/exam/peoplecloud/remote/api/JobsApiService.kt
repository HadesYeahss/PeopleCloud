package com.exam.peoplecloud.remote.api


import com.exam.peoplecloud.models.Source
import retrofit2.http.GET

interface JobsApiService {

    /**
     * GET jobs list
     */
    @GET("indeed")
    suspend fun getJobs(): Source


}