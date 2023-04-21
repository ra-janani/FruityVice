package com.example.fruityvice.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fruityvice.data.FruityViceItemModel
import com.example.fruityvice.getOrAwaitValue
import com.example.fruityvice.repository.Repository
import com.example.fruityvice.ui.FruitsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FruitsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_GetFruits_EmptyList() = runTest{
        Mockito.`when`(repository.getFruits()).thenReturn(arrayListOf())

        val vm = FruitsViewModel(repository)
        vm.getFruitsData()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = vm.fruits.getOrAwaitValue()
        assertEquals(0, result.size)
    }

    @Test
    fun test_GetFruits_expectedError() = runTest{
        Mockito.`when`(repository.getFruits()).thenReturn(null)

        val sut = FruitsViewModel(repository)
        sut.getFruitsData()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.fruits.getOrAwaitValue()
        //Assert.assertEquals(true, result )
        Assert.assertEquals(null, result)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}