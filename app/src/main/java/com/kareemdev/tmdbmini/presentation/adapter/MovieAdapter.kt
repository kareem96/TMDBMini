package com.kareemdev.tmdbmini.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kareemdev.core.data.source.remote.response.MovieResponse
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.tmdbmini.R
import com.kareemdev.tmdbmini.databinding.ItemHorizontalBinding
import com.kareemdev.tmdbmini.utils.Extensions.Companion.getRatingColorId

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHorizontalBinding.bind(itemView)

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(data: Movie) {
            val vote = data.voteAverage
            with(binding) {
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.baseUrlImage, data.posterPath))
                    .into(imgHorizontalList)
                txtHorizontalListTitle.text = data.title
                txtHorizontalListDate.text = data.releaseDate
                txtHorizontalListVote.text = String.format("%.1f", data.voteAverage)

                if (vote == .0) {
                    cardVote.visibility = View.GONE
                } else {
                    val colorId = getRatingColorId(vote, itemView)
                    rlVote.setBackgroundColor(colorId)
                }
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)
        )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}