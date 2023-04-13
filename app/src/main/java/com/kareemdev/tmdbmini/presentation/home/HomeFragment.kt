package com.kareemdev.tmdbmini.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kareemdev.core.data.Resource
import com.kareemdev.tmdbmini.databinding.FragmentHomeBinding
import com.kareemdev.tmdbmini.presentation.adapter.MovieAdapter
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity
import com.kareemdev.tmdbmini.presentation.home.more.NowPlayingActivity
import com.kareemdev.tmdbmini.presentation.home.more.PopularAllActivity
import com.kareemdev.tmdbmini.presentation.home.more.TopRatedAllActivity
import com.kareemdev.tmdbmini.presentation.home.more.UpComingAllActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            observePopular()
            observeTopRated()
            observeNowPlaying()
            observeUpComing()
        }

    }

    private fun observePopular() {
        val mAdapter = MovieAdapter()
        mAdapter.onItemClick = {select ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, select)
            startActivity(intent)
        }
        binding.morePopular.setOnClickListener {
            val intent = Intent(activity, PopularAllActivity::class.java)
            startActivity(intent)
        }
        with(binding.rvPopular) {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        viewModel.popular.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBarPopular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarPopular.visibility = View.GONE
                        mAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarPopular.visibility = View.GONE
                        binding.lblError1.text = movie.message ?: "Something Error"
                    }
                }
            }
        }
    }

    private fun observeTopRated() {
        val mAdapter = MovieAdapter()
        mAdapter.onItemClick = {select ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, select)
            startActivity(intent)
        }
        binding.moreTopRated.setOnClickListener {
            val intent = Intent(activity, TopRatedAllActivity::class.java)
            startActivity(intent)
        }
        with(binding.rvTopRated) {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        viewModel.topRated.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBarTopRated.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarTopRated.visibility = View.GONE
                        mAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarTopRated.visibility = View.GONE
                        binding.lblError2.text = movie.message ?: "Something Error"
                    }
                }
            }
        }
    }
    private fun observeNowPlaying() {
        val mAdapter = MovieAdapter()
        mAdapter.onItemClick = {select ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, select)
            startActivity(intent)
        }
        binding.moreNowPlaying.setOnClickListener {
            val intent = Intent(activity, NowPlayingActivity::class.java)
            startActivity(intent)
        }
        with(binding.rvNowPlaying) {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        viewModel.nowPlaying.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBarNowPlaying.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarNowPlaying.visibility = View.GONE
                        mAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarNowPlaying.visibility = View.GONE
                        binding.lblError3.text = movie.message ?: "Something Error"
                    }
                }
            }
        }
    }
    private fun observeUpComing() {
        val mAdapter = MovieAdapter()
        mAdapter.onItemClick = {select ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, select)
            startActivity(intent)
        }
        binding.moreUpComing.setOnClickListener {
            val intent = Intent(activity, UpComingAllActivity::class.java)
            startActivity(intent)
        }
        with(binding.rvUpComing) {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        viewModel.upComing.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBarUpComing.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarUpComing.visibility = View.GONE
                        mAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarUpComing.visibility = View.GONE
                        binding.lblError4.text = movie.message ?: "Something Error"
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}