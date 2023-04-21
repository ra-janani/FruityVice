package com.example.fruityvice.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruityvice.data.FruityViceItemModel
import com.example.fruityvice.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitsViewModel @Inject constructor(
    val repository: Repository
    ):ViewModel(){

    val fruits= MutableLiveData<ArrayList<FruityViceItemModel>>()

    fun getFruitsData(){

        viewModelScope.launch{
            val result=repository.getFruits()
            fruits.postValue(result)
        }
    }
}