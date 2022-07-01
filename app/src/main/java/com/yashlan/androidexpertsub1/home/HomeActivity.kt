/*
 * Created by Muhammad Yashlan Iskandar on 7/1/22, 7:46 PM
 * Last modified 7/1/22, 7:46 PM
 */

package com.yashlan.androidexpertsub1.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.yashlan.androidexpertsub1.R
import com.yashlan.androidexpertsub1.databinding.ActivityHomeBinding
import com.yashlan.androidexpertsub1.detail.DetailMovieActivity
import com.yashlan.androidexpertsub1.detail.DetailMovieViewModel
import com.yashlan.androidexpertsub1.setting.SettingsActivity
import com.yashlan.core.data.Resource
import com.yashlan.core.ui.MovieAdapter
import com.yashlan.core.utils.forcePortraitScreenOrientation
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeVieModel: HomeViewModel by viewModel()
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        forcePortraitScreenOrientation()
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        movieAdapter.onFavButtonClick = { movie, isNotLiked ->
            if (isNotLiked) {
                detailMovieViewModel.setFavoriteMovie(movie, true)
            } else {
                detailMovieViewModel.setFavoriteMovie(movie, false)
            }
        }

        homeVieModel.movie.observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Success -> {
                        movieAdapter.setData(movie.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        binding.ivError.visibility = View.VISIBLE
                        showLoading(false)
                    }
                }
            }
        }

        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_fav -> {
                val uri = Uri.parse("moviesapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            R.id.action_setting -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                rvMovie.visibility = View.GONE
                progressLoading.visibility = View.VISIBLE
            } else {
                rvMovie.visibility = View.VISIBLE
                progressLoading.visibility = View.GONE
            }
        }
    }
}