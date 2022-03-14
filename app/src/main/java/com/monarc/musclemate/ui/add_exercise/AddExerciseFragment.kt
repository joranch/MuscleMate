package com.monarc.musclemate.ui.add_exercise

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.databinding.FragmentAddExerciseBinding
import com.monarc.musclemate.ui.add_exercise.adapters.ExerciseListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddExerciseFragment : Fragment() {

    private val viewModel: AddExerciseViewModel by viewModels()
    private var _binding: FragmentAddExerciseBinding? = null
    private val binding get() = _binding!!

    private lateinit var exerciseListAdapter: ExerciseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        exerciseListAdapter =
            ExerciseListAdapter(
                onItemClicked = { exercise -> onExerciseClicked(exercise) },
                exerciseInfoClicked = { exercise -> onExerciseInfoClicked(exercise)}
            )

        binding.workoutRecyclerView.adapter = exerciseListAdapter
        binding.workoutRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        addChips()
        subscribeToObservables()
        viewModel.fetchExercisesFromApi()
    }

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.exercises.collectLatest {
                it.let {
                    exerciseListAdapter.submitList(it.toList())
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.showToast.collectLatest {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onExerciseClicked(exercise: Exercise) {
        viewModel.showToastMessage("Item click for ${exercise.name}")
    }

    private fun onExerciseInfoClicked(exercise: Exercise) {
        viewModel.showToastMessage("Info click for ${exercise.name}")
    }

    private fun addChips() {
//        val chipGroup = binding.chipGroup
//        viewModel.muscleList.forEach { muscle ->
//            chipGroup.addView(createChip(muscle.name))
//        }

        viewModel.exerciseCategoryList.forEach { category ->
            binding.chipGroupCategory.addView(createChip(category.name))
        }
    }

    private fun createChip(labelText: String): View {
        val chip = Chip(requireContext())
        return chip.apply {
            id = ViewCompat.generateViewId()
            text = labelText
            isCheckable = true
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}