package com.kareemdev.tmdbmini.presentation.detail.trailers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.tmdbmini.databinding.FragmentTrailersBinding
import com.kareemdev.tmdbmini.presentation.adapter.TrailersAdapter
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity.Companion.EXTRA_DATA
import com.kareemdev.tmdbmini.presentation.detail.DetailViewModel
import com.kareemdev.tmdbmini.presentation.detail.video.VideoPlayerActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [TrailersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TrailersFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentTrailersBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrailersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailMovie = activity?.intent?.getParcelableExtra<Movie>(EXTRA_DATA)
        if (activity != null) {
            initView(detailMovie)
        }
    }

        private fun trailerClick(videoId: String) {
            /*val action = TrailersFragmentDirections.actionTrailersFragmentToVideoPlayerFragment(
                videoId,
                0,
            )
            findNavController().navigate(action)*/

    }

    private fun initView(detailMovie: Movie?) {

        val mAdapter = TrailersAdapter()
        mAdapter.onClick = { select ->
            binding.youtubePlayerView.visibility = View.VISIBLE
            /*val intent = Intent(activity, VideoPlayerActivity::class.java)
            intent.putExtra(VideoPlayerActivity.EXTRA_DATA, select)
            startActivity(intent)*/
        }
        with(binding.rvTrailer) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            setHasFixedSize(true)
        }


        if (detailMovie != null) {
            viewModel.getTrailer(detailMovie.movieId).observe(viewLifecycleOwner) { trailer ->
                if (trailer != null) {
                    when (trailer) {
                        is Resource.Success -> {
                            mAdapter.setData(trailer.data)
//                            mAdapter.onClick =
                        }

                        is Resource.Error -> {
                            Log.d("error", "initView: ${trailer.message}")
                        }

                        is Resource.Loading -> {
                            Log.d("loading", "showLoading:")
                        }
                    }
                }
            }
        }
    }
}