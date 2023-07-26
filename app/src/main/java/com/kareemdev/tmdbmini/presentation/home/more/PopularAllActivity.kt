package com.kareemdev.tmdbmini.presentation.home.more

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.tmdbmini.databinding.ActivityPopularAllBinding
import com.kareemdev.tmdbmini.presentation.adapter.MovieAdapter
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity
import com.kareemdev.tmdbmini.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularAllActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPopularAllBinding
    private lateinit var mAdapter: MovieAdapter
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Popular"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        mAdapter = MovieAdapter()
        mAdapter.onItemClick = {select ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, select)
            startActivity(intent)
        }
        with(binding){
            rvAllPopular.layoutManager = GridLayoutManager(this@PopularAllActivity, 3)
            rvAllPopular.hasFixedSize()
            rvAllPopular.adapter = mAdapter
            rvAllPopular.isNestedScrollingEnabled = false
        }
    }

    private fun setupObserver(){
        viewModel.popular.observe(this){
            when(it){
                is Resource.Success ->{
                    val data = it.data as ArrayList<Movie>
                    if(!data.isNullOrEmpty()){
                        mAdapter.setData(data)

                    }
                }
                is Resource.Error ->{
                    Log.d("TAG", "setupObserver Error: ")
                }
                is Resource.Loading ->{
                    Log.d("TAG", "setupObserver: Loading")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.root.removeAllViewsInLayout()
    }

}