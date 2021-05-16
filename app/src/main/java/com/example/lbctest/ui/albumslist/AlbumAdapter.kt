package com.example.lbctest.ui.albumslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.lbctest.databinding.ItemAlbumBinding
import com.example.lbctest.domain.models.Album

class AlbumAdapter(val context : Context ,private val function: (Album) -> Unit)
    : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(DiffCallbackEpgDay()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
            AlbumViewHolder.from(parent,context)

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
            holder.bind(context,getItem(position), function)

    class AlbumViewHolder(
            private val binding: ItemAlbumBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup, context: Context): AlbumViewHolder {
                val binding = ItemAlbumBinding.inflate(LayoutInflater.from(context),
                        parent,
                        false)
                return AlbumViewHolder(binding)
            }
        }

        fun bind(context: Context,item: Album, function: (Album) -> Unit) {
            binding.apply {

                Glide.with(context)
                        .load(getGlideUrl(item.songs[0].thumbnailUrl))
                        .centerCrop()
                        .into(itemAlbumPreview1)
                Glide.with(context)
                        .load(getGlideUrl(item.songs[1].thumbnailUrl))
                        .centerCrop()
                        .into(itemAlbumPreview2)
                Glide.with(context)
                        .load(getGlideUrl(item.songs[2].thumbnailUrl))
                        .centerCrop()
                        .into(itemAlbumPreview3)
                Glide.with(context)
                        .load(getGlideUrl(item.songs[3].thumbnailUrl))
                        .centerCrop()
                        .into(itemAlbumPreview4)
                itemAlbumTitle.text = "Album : ${item.id} "
                itemAlbumDescription.text = "${item.songs.size} songs"
                root.setOnClickListener { function(item) }
            }
        }
    }
}

private fun getGlideUrl(url : String) =  GlideUrl(url, LazyHeaders.Builder()
        .addHeader("User-Agent", "your-user-agent")
        .build())

class DiffCallbackEpgDay : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean = oldItem == newItem
}