package com.monarc.musclemate.ui.workout

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.monarc.musclemate.R
import com.monarc.musclemate.databinding.WorkoutDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

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
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.loadWorkoutRoutineData()

        binding.addExerciseButton.setOnClickListener {
            viewModel.getExercises()
        }

//        lifecycleScope.launchWhenStarted {
//            binding.viewModel.workoutRoutine.collectLatest {
//
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.workout_detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_button -> {
                viewModel.saveWorkoutRoutine()
                findNavController().popBackStack()
            }
        }
        return true
    }

    private fun saveWorkoutRoutine() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}