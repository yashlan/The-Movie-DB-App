/*
 * Created by Muhammad Yashlan Iskandar on 7/12/22, 4:41 PM
 * Last modified 7/12/22, 4:41 PM
 */

package com.yashlan.androidexpertsub2.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.yashlan.androidexpertsub2.api.FakeApi
import com.yashlan.androidexpertsub2.utils.DataSample
import com.yashlan.androidexpertsub2.utils.LiveDataTestUtil.getOrAwaitValue
import com.yashlan.androidexpertsub2.utils.MainCoroutineRule
import com.yashlan.androidexpertsub2.utils.Result
import com.yashlan.androidexpertsub2.viewmodel.FakeHomeViewModel
import com.yashlan.core.data.source.local.entity.MovieEntity
import com.yashlan.core.data.source.remote.network.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var viewModel: FakeHomeViewModel

    @Mock
    private lateinit var apiService: ApiService

    private val dummyMoviesEntity = DataSample.generateDummyMoviesEntity()

    @Before
    fun setUp() {
        apiService = FakeApi()
        viewModel = mock(FakeHomeViewModel::class.java)
    }

    @Test
    fun moviesResponseShouldBeNotNull() = mainCoroutineRule.runBlockingTest {
        val expectedMovies = DataSample.generateDummyMoviesResponse()
        val actualMovies = apiService.getListMovie()
        assertNotNull(actualMovies)
        assertEquals(expectedMovies.results.size, actualMovies.results.size)
    }

    @Test
    fun moviesEntityShouldBeNotNullAndReturnSuccess() {
        val expectedMovieEntity = MutableLiveData<Result<List<MovieEntity>>>()
        expectedMovieEntity.value = Result.Success(dummyMoviesEntity)
        `when`(viewModel.getAllMovieEntity()).thenReturn(expectedMovieEntity)
        val actualMovieEntity = viewModel.getAllMovieEntity().getOrAwaitValue()
        verify(viewModel).getAllMovieEntity()
        assertNotNull(actualMovieEntity)
        assertTrue(actualMovieEntity is Result.Success)
        assertEquals(dummyMoviesEntity.size, (actualMovieEntity as Result.Success).data.size)
    }
}