package com.kareemdev.tmdbmini.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.tmdbmini.R
import com.kareemdev.tmdbmini.databinding.ItemTrailerBinding
import com.kareemdev.tmdbmini.utils.Extensions.Companion.loadImage
import com.kareemdev.tmdbmini.utils.ImageTypeEnum

class TrailersAdapter : RecyclerView.Adapter<TrailersAdapter.ListViewHolder>() {
    private var listData = ArrayList<Trailers>()
//    var onClick: (String) -> Unit = {}
    var onClick: ((Trailers) -> Unit)? = null
    fun setData(newListData: List<Trailers>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTrailerBinding.bind(itemView)

        fun bind(data: Trailers) {
            with(binding) {
                tvTitle.text = data.name
                tvDate.text = data.published
                ivVideo.loadImage(data.key, imageTypeEnum = ImageTypeEnum.YOUTUBE)

                /*root.setOnClickListener {
                    *//*onClick(data.key)
                    Toast.makeText(itemView.context, data.key, Toast.LENGTH_SHORT).show()*//*
                }
                mcvVideo.setOnClickListener {
                    *//*onClick(data.key)
                    Toast.makeText(itemView.context, data.key, Toast.LENGTH_SHORT).show()*//*
                }*/
            }
        }
        init {
            binding.root.setOnClickListener {
                onClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_trailer, parent, false)
    )

    override fun onBindViewHolder(holder: TrailersAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}