/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 3:36 PM
 * Last modified 6/26/22, 3:36 PM
 */

package com.yashlan.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.yashlan.core.domain.model.Movie

class DiffUtilCallback(
    private val oldListData: List<Movie>,
    private val newListData: List<Movie>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldListData.size

    override fun getNewListSize(): Int = newListData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldListData[oldItemPosition].movieId == newListData[newItemPosition].movieId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldListData.containsAll(newListData) && newListData.containsAll(oldListData)
}