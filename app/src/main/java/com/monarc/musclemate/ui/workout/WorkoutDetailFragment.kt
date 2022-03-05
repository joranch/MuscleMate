package com.monarc.musclemate.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.monarc.musclemate.databinding.WorkoutDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class WorkoutDetailFragment : Fragment() {

    private val viewModel: WorkoutDetailViewModel by viewModels()
    private val navigationArgs: WorkoutDetailFragmentArgs by navArgs()

    private var _binding: WorkoutDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WorkoutDetailFragmentBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.addExerciseButton.setOnClickListener {
            viewModel.getExercises()
        }

//        lifecycleScope.launchWhenStarted {
//            binding.viewModel.workoutRoutine.collectLatest {
//
//            }
//        }

        viewModel.getWorkoutRoutine(navigationArgs.workoutId)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}