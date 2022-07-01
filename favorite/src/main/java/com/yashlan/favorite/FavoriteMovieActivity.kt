/*
 * Created by Muhammad Yashlan Iskandar on 7/1/22, 3:18 PM
 * Last modified 7/1/22, 3:13 PM
 */

package com.yashlan.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.yashlan.androidexpertsub1.detail.DetailMovieActivity
import com.yashlan.core.data.Resource
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.ui.MovieAdapter
import com.yashlan.favorite.databinding.ActivityFavoriteMovieBinding
import com.yashlan.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteMovieBinding
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"

        getFavorite()
    }

    private fun getFavorite() {
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        movieAdapter.onFavButtonClick = { movie, isNotLiked ->
            if (!isNotLiked) {
               // detailMovieViewModel.setFavoriteMovie(movie, false)
            }
        }

        favoriteMovieViewModel.favoriteMovie.observe(this) { listFav: List<Movie>? ->
            if(listFav != null) {
                when(listFav) {
                    is Resource.Loading<*> -> {

                    }
                    is Resource.Success<*> -> {
                        movieAdapter.setData(listFav)
                    }
                    is Resource.Error<*> -> {

                    }
                }
            }
        }

        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}