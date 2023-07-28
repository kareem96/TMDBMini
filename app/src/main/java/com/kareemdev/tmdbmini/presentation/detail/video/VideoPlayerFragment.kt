package com.kareemdev.tmdbmini.presentation.detail.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.tmdbmini.R
import com.kareemdev.tmdbmini.databinding.FragmentVideoPlayerBinding
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity.Companion.EXTRA_DATA
import com.kareemdev.tmdbmini.presentation.detail.DetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [VideoPlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private val args: VideoPlayerFragmentArgs by navArgs()
    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailMovie = activity?.intent?.getParcelableExtra<Movie>(EXTRA_DATA)
        if(activity != null){
            initView(detailMovie)
        }
    }

    private fun initView(detailMovie: Movie?) {
        with(binding){
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

            lifecycle.addObserver(ytPlayer)
            args.videoId?.let {
                ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(it, 0f)
                    }
                })
            }

            if(detailMovie != null){
                viewModel.getTrailer(detailMovie.movieId).observe(viewLifecycleOwner){trailer ->
                    if(trailer != null){
                        when(trailer){
                            is Resource.Success ->{
                                if(args.videoId == null && trailer.data?.isNotEmpty() == true){
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
            }
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

}