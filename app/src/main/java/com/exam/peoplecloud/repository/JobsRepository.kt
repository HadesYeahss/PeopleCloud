package com.exam.peoplecloud.repository


import com.exam.peoplecloud.models.Source
import com.exam.peoplecloud.remote.api.JobsApiService
import com.exam.peoplecloud.remote.responses.BaseApiResponse
import com.exam.peoplecloud.remote.responses.ResponseResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class JobsRepository @Inject constructor(
    private val indexApiService: JobsApiService
) : BaseApiResponse() {

    suspend fun getJobs(): Flow<ResponseResult<Source>> {
        return flow {
                emit(safeApiCall { indexApiService.getJobs() })
        }.flowOn(Dispatchers.IO)

    }

}
