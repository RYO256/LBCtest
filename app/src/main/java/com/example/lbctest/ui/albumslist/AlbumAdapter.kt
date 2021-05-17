package com.example.lbctest.ui.albumslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lbctest.databinding.ItemAlbumBinding
import com.example.lbctest.domain.models.Album
import com.example.lbctest.ui.getGlideUrl

class AlbumAdapter(private val onClickAction: (Album) -> Unit)
    : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(DiffCallbackEpgDay) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
            AlbumViewHolder.from(parent)

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
            holder.bind(getItem(position), onClickAction)

    class AlbumViewHolder(
            private val binding: ItemAlbumBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): AlbumViewHolder {
                val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),
                        parent,
                        false)
                return AlbumViewHolder(binding)
            }
        }

        fun bind(item: Album, onClickAction: (Album) -> Unit) {
            binding.apply {

                if (item.songs.size > 4) {
                    Glide.with(itemAlbumPreview1.context)
                            .load(getGlideUrl(item.songs[0].thumbnailUrl))
                            .centerCrop()
                            .into(itemAlbumPreview1)
                    Glide.with(itemAlbumPreview2.context)
                            .load(getGlideUrl(item.songs[1].thumbnailUrl))
                            .centerCrop()
                            .into(itemAlbumPreview2)
                    Glide.with(itemAlbumPreview3.context)
                            .load(getGlideUrl(item.songs[2].thumbnailUrl))
                            .centerCrop()
                            .into(itemAlbumPreview3)
                    Glide.with(itemAlbumPreview4.context)
                            .load(getGlideUrl(item.songs[3].thumbnailUrl))
                            .centerCrop()
                            .into(itemAlbumPreview4)
                }
                itemAlbumTitle.text = "Album : ${item.id} "
                itemAlbumDescription.text = "${item.songs.size} songs"
                root.setOnClickListener { onClickAction(item) }
            }
        }
    }
}

object DiffCallbackEpgDay : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean = oldItem == newItem
}