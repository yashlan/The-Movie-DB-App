/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:31 AM
 * Last modified 6/26/22, 12:31 AM
 */

package com.yashlan.androidexpertsub1.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.yashlan.androidexpertsub1.databinding.FragmentFavoriteMovieBinding
import com.yashlan.androidexpertsub1.detail.DetailMovieActivity
import com.yashlan.androidexpertsub1.detail.DetailMovieViewModel
import com.yashlan.core.R
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.ui.MovieAdapter
import com.yashlan.core.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
                startActivity(intent)
            }

            movieAdapter.onFavButtonClick = { movie, isNotLiked ->
                if (!isNotLiked) {
                    detailMovieViewModel.setFavoriteMovie(movie, false)
                }
            }

            favoriteMovieViewModel.favoriteMovie.observe(viewLifecycleOwner) { listFavorite: List<Movie>? ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}