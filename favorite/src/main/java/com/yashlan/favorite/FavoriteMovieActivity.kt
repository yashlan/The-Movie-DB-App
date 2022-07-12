/*
 * Created by Muhammad Yashlan Iskandar on 7/1/22, 3:18 PM
 * Last modified 7/1/22, 3:13 PM
 */

package com.yashlan.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.yashlan.androidexpertsub1.detail.DetailMovieActivity
import com.yashlan.androidexpertsub1.detail.DetailMovieViewModel
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.ui.MovieAdapter
import com.yashlan.core.utils.ScreenUtil
import com.yashlan.favorite.databinding.ActivityFavoriteMovieBinding
import com.yashlan.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteMovieBinding
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        ScreenUtil.forcePortraitScreenOrientation(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getListFavorite()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvFavorite.adapter = null
    }

    private fun getListFavorite() {
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        movieAdapter.onFavButtonClick = { movie, isNotLiked ->
            if (!isNotLiked) {
                detailMovieViewModel.setFavoriteMovie(movie, false)
            }
        }

        favoriteMovieViewModel.favoriteMovie.observe(this) { listFavorite: List<Movie>? ->
            movieAdapter.setData(listFavorite)
            binding.tvEmptyList.visibility =
                if (listFavorite.isNullOrEmpty()) View.VISIBLE else View.GONE
        }

        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}