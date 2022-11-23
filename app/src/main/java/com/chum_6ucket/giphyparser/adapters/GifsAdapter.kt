package com.chum_6ucket.giphyparser.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chum_6ucket.giphyparser.adapters.GifsAdapter.GifsViewHolder
import com.chum_6ucket.giphyparser.data.models.DataItem
import com.chum_6ucket.giphyparser.databinding.ListItemGifBinding

class GifsAdapter : PagingDataAdapter<DataItem, GifsViewHolder>(GifsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        return GifsViewHolder(
            ListItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif)
        }
    }

    class GifsViewHolder(
        private val binding: ListItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem) {
            binding.apply {
                gif = item
                executePendingBindings()
            }
        }
    }
}

private class GifsDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ): Boolean {
        return oldItem == newItem
    }
}