package com.example.lbctest.ui.albumdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.lbctest.databinding.ItemSongBinding
import com.example.lbctest.domain.models.Song

class SongAdapter(val context : Context ,private val function: (Song) -> Unit)
    : ListAdapter<Song, SongAdapter.SongViewHolder>(DiffCallbackEpgDay()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
            SongViewHolder.from(parent, context)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
            holder.bind(context,getItem(position), function)

    class SongViewHolder(
            private val binding: ItemSongBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup, context: Context): SongViewHolder {
                val binding = ItemSongBinding.inflate(LayoutInflater.from(context),
                        parent,
                        false)
                return SongViewHolder(binding)
            }
        }

        fun bind(context: Context,item: Song, function: (Song) -> Unit) {
            binding.apply {
                Glide.with(context)
                        .load(getGlideUrl(item.thumbnailUrl))
                        .centerCrop()
                        .into(itemSongPreview)
                itemSongTitle.text = item.title
                root.setOnClickListener { function(item) }
            }
        }
    }
}

private fun getGlideUrl(url : String) =  GlideUrl(url, LazyHeaders.Builder()
        .addHeader("User-Agent", "your-user-agent")
        .build())

class DiffCallbackEpgDay : DiffUtil.ItemCallback<Song>() {

    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem == newItem
}