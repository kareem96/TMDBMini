package com.kareemdev.tmdbmini.presentation.detail.review

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
import com.kareemdev.tmdbmini.databinding.FragmentReviewBinding
import com.kareemdev.tmdbmini.presentation.adapter.ReviewAdapter
import com.kareemdev.tmdbmini.presentation.detail.DetailActivity.Companion.EXTRA_DATA
import com.kareemdev.tmdbmini.presentation.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ReviewFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailMovie = activity?.intent?.getParcelableExtra<Movie>(EXTRA_DATA)
        if(activity != null){
            initView(detailMovie)
        }
    }

    private fun initView(detailMovie:Movie?) {
        val mAdapter = ReviewAdapter()
        with(binding.rvReview){
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
        if (detailMovie != null) {
            viewModel.getReview(detailMovie.movieId).observe(viewLifecycleOwner){ review ->
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

}