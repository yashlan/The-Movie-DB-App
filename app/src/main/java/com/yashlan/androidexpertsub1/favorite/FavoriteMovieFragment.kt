/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:31 AM
 * Last modified 6/26/22, 12:31 AM
 */

package com.yashlan.androidexpertsub1.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yashlan.androidexpertsub1.databinding.FragmentFavoriteMovieBinding

class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoriteViewModel =
            ViewModelProvider(this)[FavoriteMovieViewModel::class.java]

        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView = binding.textFav
        textView.text = "test"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}