package com.kareemdev.tmdbmini.presentation.detail.video

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.tmdbmini.R
import com.kareemdev.tmdbmini.databinding.ActivityVideoPlayerBinding
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity.Companion.EXTRA_DATA
import com.kareemdev.tmdbmini.presentation.detail.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val trailer = intent.getParcelableExtra<Trailers>(EXTRA_DATA)
        initView(trailer)
    }


    private fun initView(trailer: Trailers?) {

        with(binding){
            this@VideoPlayerActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            this@VideoPlayerActivity.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            this@VideoPlayerActivity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

            lifecycle.addObserver(ytPlayer)
            trailer?.id.let {
                ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    var videoIndex = 0
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        if (it != null) {
                            youTubePlayer.loadVideo(it, 0f)
                        }
                    }
                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        super.onStateChange(youTubePlayer, state)
                        if (state == PlayerConstants.PlayerState.ENDED) {
                            if (videoIndex < it!!.length - 1) {
                                videoIndex++
                                youTubePlayer.loadVideo(
                                    it[videoIndex].toString(),
                                    0f
                                )
                            }
                        }
                    }
                })
            }

            /*if(movie != null){
                viewModel.getTrailer(movie.movieId).observe(this@VideoPlayerActivity){trailer ->
                    if(trailer != null){
                        when(trailer){
                            is Resource.Success ->{
                                if(movie.movieId == null && trailer.data?.isNotEmpty() == true){
                                    ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                        var videoIndex = 0
                                        override fun onReady(youTubePlayer: YouTubePlayer) {
                                            youTubePlayer.loadVideo(trailer.data!![videoIndex].key ?: "", 0f)
                                        }

                                        override fun onStateChange(
                                            youTubePlayer: YouTubePlayer,
                                            state: PlayerConstants.PlayerState
                                        ) {
                                            super.onStateChange(youTubePlayer, state)
                                            if(state == PlayerConstants.PlayerState.ENDED){
                                                if(videoIndex < (trailer.data?.size?.minus(1) ?: 0)){
                                                    videoIndex++
                                                    youTubePlayer.loadVideo(trailer.data!![videoIndex].key, 0f)
                                                }
                                            }
                                        }

                                    })
                                }
                            }
                            is Resource.Loading ->{
                                Log.d("Loading", "initView: ")
                            }
                            is Resource.Error ->{
                                Log.d("Error", "${trailer.message}")
                            }
                        }
                    }
                }
            }*/
        }
    }
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}