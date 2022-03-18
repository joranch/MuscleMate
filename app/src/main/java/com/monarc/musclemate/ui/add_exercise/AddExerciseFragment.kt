package com.monarc.musclemate.ui.add_exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.android.material.chip.Chip
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.databinding.FragmentAddExerciseBinding
import com.monarc.musclemate.ui.add_exercise.adapters.ExerciseListAdapter
import com.monarc.musclemate.workers.DownloadExercisesWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
                exerciseInfoClicked = { exercise -> onExerciseInfoClicked(exercise) }
            )

        binding.workoutRecyclerView.adapter = exerciseListAdapter
        binding.workoutRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        addChips()
        subscribeToObservables()
        fetchExercisesIfHasNone()
    }

    private fun fetchExercisesIfHasNone() {
        lifecycleScope.launch {
            if (!viewModel.checkHasExercises()
                && viewModel.apiEvent.value !is AddExerciseViewModel.ApiEvent.Loading
            ) {
                enqueueExercisesWorker()
            }
        }
    }

    private fun enqueueExercisesWorker() {
        val exercisesWorker = OneTimeWorkRequestBuilder<DownloadExercisesWorker>()
            .addTag(DownloadExercisesWorker.REQUEST_TAG)
            .build()

        WorkManager.getInstance(requireContext()).enqueueUniqueWork(
            DownloadExercisesWorker.REQUEST_TAG,
            ExistingWorkPolicy.KEEP,
            exercisesWorker
        )
    }

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.exercises.collectLatest {
                it.let {
                    exerciseListAdapter.submitList(it.toList())
                }
            }

            viewModel.apiEvent.collectLatest { event ->
                when (event) {
                    is AddExerciseViewModel.ApiEvent.Empty -> {
                        viewModel.showToastMessage("Download exercises complete")
                    }
                    is AddExerciseViewModel.ApiEvent.Loading -> {
                        viewModel.showToastMessage("Loading exercises")
                    }
                }
            }

            viewModel.showToast.collectLatest {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.workerInfo.observe(viewLifecycleOwner, workerObserver())
    }

    private fun workerObserver(): Observer<MutableList<WorkInfo>> {
        return Observer { workInfos ->

            if (workInfos.isNullOrEmpty()) {
                return@Observer
            }

            val workInfo = workInfos.first()

            if (!workInfo.state.isFinished) {
                viewModel.setExercisesLoading(true)
            } else {
                viewModel.setExercisesLoading(false)
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