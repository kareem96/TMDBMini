package com.kareemdev.tmdbmini.presentation.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kareemdev.core.data.Resource
import com.kareemdev.core.data.source.remote.response.MovieResponse
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.tmdbmini.R
import com.kareemdev.tmdbmini.databinding.ActivityDetailBinding
import com.kareemdev.tmdbmini.presentation.adapter.ReviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = binding.content.viewPager
        val adapter = ViewPagerAdapter(this)
        val tabLayout : TabLayout = binding.content.tabLyt
        viewPager.adapter = adapter
        val tabsArray = arrayOf(
            "Trailers",
            "Review",
        )
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = tabsArray[position]
        }.attach()

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    override fun onBackPressed() {
        onBackPressedDispatcher
    }
    override fun supportNavigateUpTo(upIntent: Intent) {
        super.supportNavigateUpTo(upIntent)
        onBackPressed()
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {

//            binding.content.lblName.text = detailMovie.title
            binding.content.tvDetailDescription.text = detailMovie.overview
            binding.content.tvRatingCount.text = detailMovie.createVoteCountToString()
            binding.content.tvPopularity.text = detailMovie.popularity.toString()
            binding.content.tvReleaseDate.text = detailMovie.releaseDate
            supportActionBar?.title = detailMovie.title
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, detailMovie.posterPath))
                .into(binding.ivDetailImage)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }

        val mAdapter = ReviewAdapter()
        /*with(binding.content.rvReview){
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }*/
        if (detailMovie != null) {
            detailViewModel.getReview(detailMovie.movieId).observe(this){ review ->
                if (review != null){
                    when(review){
                        is Resource.Success ->{
                            mAdapter.setData(review.data)
                            Log.d("Success", "showDetailMovie: ")
                        }
                        is Resource.Error ->{
                            Log.d("error", "${review.message}")
                        }
                        is Resource.Loading ->{
                            Log.d("loading", "showDetailMovie: ")
                        }
                    }
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if(statusFavorite){
            binding.fab.setImageDrawable(

                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        }else{
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )

        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}