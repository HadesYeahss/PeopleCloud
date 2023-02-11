package com.exam.peoplecloud.di

import com.exam.peoplecloud.remote.api.JobsApiService
import com.exam.peoplecloud.repository.JobsRepository
import com.exam.peoplecloud.utils.Constants
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val loggerInterceptor = LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .build()
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        return OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .addInterceptor(loggerInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): SimpleXmlConverterFactory =
        SimpleXmlConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        simpleConverterFactory: SimpleXmlConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(simpleConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideIndexApiService(retrofit: Retrofit): JobsApiService =
        retrofit.create(JobsApiService::class.java)

    @Singleton
    @Provides
    fun provideIndexRepository(indexApiService : JobsApiService) : JobsRepository{
        return  JobsRepository(indexApiService)
    }



}