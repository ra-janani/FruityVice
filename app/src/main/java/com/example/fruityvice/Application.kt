package com.example.fruityvice

import android.app.Application
import com.example.fruityvice.remote.ApiDetails.BASE_URL
import com.example.fruityvice.remote.ApiRequest
import com.example.fruityvice.repository.Repository
import com.example.fruityvice.repository.RepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class Application : Application(){
    lateinit var fruitsAPI: ApiRequest
    lateinit var fruitsRepository: Repository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        fruitsAPI = retrofit.create(ApiRequest::class.java)
        fruitsRepository = RepositoryImpl(fruitsAPI)
    }
}