/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:20 PM
 * Last modified 6/25/22, 10:20 PM
 */

package com.yashlan.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashlan.core.BuildConfig
import com.yashlan.core.R
import com.yashlan.core.databinding.ItemListMovieBinding
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.utils.DiffUtilCallback

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var oldListData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null
    var onFavButtonClick: ((Movie, Boolean) -> Unit?)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        val diffUtil = DiffUtilCallback(oldListData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldListData.clear()
        oldListData = newListData as ArrayList<Movie>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_movie,
                parent,
                false
            )
        )

    override fun getItemCount() = oldListData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = oldListData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_DETAIL_URL + movie.posterPath)
                    .into(ivPoster)
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate.substring(0, 4)
                favButton.apply {
                    isLiked = false
                    setOnClickListener {
                        isLiked = !isLiked
                        onFavButtonClick?.invoke(movie, isLiked)
                    }
                }
                if(movie.isFavorite) {
                    favButton.isLiked = true
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(oldListData[adapterPosition])
            }
        }
    }
}