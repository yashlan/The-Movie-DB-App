/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:25 AM
 * Last modified 6/25/22, 4:54 PM
 */

package com.yashlan.androidexpertsub1.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.yashlan.core.R
import com.yashlan.androidexpertsub1.databinding.FragmentHomeBinding
import com.yashlan.androidexpertsub1.detail.DetailMovieActivity
import com.yashlan.androidexpertsub1.detail.DetailMovieActivity.Companion.EXTRA_MOVIE
import com.yashlan.androidexpertsub1.detail.DetailMovieViewModel
import com.yashlan.core.data.Resource
import com.yashlan.core.ui.MovieAdapter
import com.yashlan.core.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeVieModel: HomeViewModel by viewModel()
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(EXTRA_MOVIE, it)
                startActivity(intent)
            }

            movieAdapter.onFavButtonClick = { movie, isNotLiked ->
                if (isNotLiked) {
                    detailMovieViewModel.setFavoriteMovie(movie, true)
                    context?.showToast(R.string.added_to_favorite)
                } else {
                    detailMovieViewModel.setFavoriteMovie(movie, false)
                }
            }

            homeVieModel.movie.observe(viewLifecycleOwner) { movie ->
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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