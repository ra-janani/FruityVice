package com.example.fruityvice.repository

import com.example.fruityvice.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest:ApiRequest
    ):Repository {

    override suspend fun getFruits() = apiRequest.getFruits()
}