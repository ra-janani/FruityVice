package com.example.fruityvice.repository

import com.example.fruityvice.data.FruityViceItemModel

interface Repository {

    suspend fun getFruits(): ArrayList<FruityViceItemModel>
}