package com.kareemdev.tmdbmini.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kareemdev.core.domain.model.Review
import com.kareemdev.tmdbmini.R
import com.kareemdev.tmdbmini.databinding.ItemReviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ListViewHolder>(){
    private var listData = ArrayList<Review>()
    fun setData(newListData: List<Review>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemReviewBinding.bind(itemView)

        fun bind(data: Review){
            with(binding){
                tvAuthors.text = data.author
                tvContent.text = data.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ListViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}