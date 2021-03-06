/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:29 AM
 * Last modified 6/26/22, 12:29 AM
 */

package com.yashlan.androidexpertsub2.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import com.bumptech.glide.Glide
import com.like.LikeButton
import com.like.OnLikeListener
import com.yashlan.androidexpertsub2.databinding.ActivityDetailMovieBinding
import com.yashlan.androidexpertsub2.detail.DetailWebViewMovie.Companion.MOVIE_ID_EXTRA
import com.yashlan.core.BuildConfig
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.utils.ScreenUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ScreenUtil.forcePortraitScreenOrientation(this)
        ScreenUtil.setFullscreen(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        showDetailsMovie(movie)

        binding.btnOpenUrl.setOnClickListener {
            it.startAnimation(AlphaAnimation(it.alpha, .7f))
            val i = Intent(this, DetailWebViewMovie::class.java)
            i.putExtra(MOVIE_ID_EXTRA, movie?.movieId)
            startActivity(i)
        }
    }

    private fun showDetailsMovie(movie: Movie?) {
        movie?.let {
            with(binding) {
                Glide.with(this@DetailMovieActivity)
                    .load(BuildConfig.IMAGE_DETAIL_URL + movie.posterPath)
                    .into(detailIvPoster)
                detailTvTitle.text = movie.title
                ("Release : " + movie.releaseDate).also { detailTvReleaseDate.text = it }
                detailTvOverview.text = movie.overview
                detailFavButton.isLiked = it.isFavorite
                setStatusFavorite(movie)
            }
        }
    }

    private fun setStatusFavorite(movie: Movie) {
        binding.detailFavButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                detailMovieViewModel.setFavoriteMovie(movie, true)
            }

            override fun unLiked(likeButton: LikeButton?) {
                detailMovieViewModel.setFavoriteMovie(movie, false)
            }
        })
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}