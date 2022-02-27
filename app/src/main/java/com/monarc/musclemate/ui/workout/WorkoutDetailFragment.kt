package com.monarc.musclemate.ui.workout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monarc.musclemate.R

class WorkoutDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WorkoutDetailFragment()
    }

    private lateinit var viewModel: WorkoutDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.workout_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WorkoutDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}