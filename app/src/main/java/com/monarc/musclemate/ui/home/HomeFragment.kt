package com.monarc.musclemate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.databinding.FragmentHomeBinding
import com.monarc.musclemate.ui.home.adapters.WorkoutListAdapter
import com.monarc.musclemate.workers.DownloadExercisesWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutListAdapter: WorkoutListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        workoutListAdapter = WorkoutListAdapter { workoutPlan -> onWorkoutPlanItemClick(workoutPlan) }
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.workoutRecyclerView.layoutManager = layoutManager
        binding.workoutRecyclerView.adapter = workoutListAdapter

        subscribeToObservables()

        binding.fab.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToWorkoutDetailFragment(WorkoutRoutine.NEW_WORKOUT_ROUTINE_ID))
        }

        createFetchExercisesWorker()
    }

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.workoutRoutines.collectLatest {
                it.let {
                    workoutListAdapter.submitList(it.toList())
                }
            }
        }
    }

    private fun onWorkoutPlanItemClick(workoutRoutine: WorkoutRoutine) {
        findNavController().navigate(HomeFragmentDirections.actionNavHomeToWorkoutDetailFragment(workoutRoutine.id))
    }

    private fun createFetchExercisesWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<DownloadExercisesWorker>(7, TimeUnit.DAYS)
                .setConstraints(constraints).addTag(DownloadExercisesWorker.REQUEST_TAG)
                .build()

        WorkManager.getInstance(requireContext()).enqueue(periodicWorkRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}