package com.monarc.musclemate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.monarc.musclemate.data.entities.WorkoutPlan
import com.monarc.musclemate.databinding.FragmentHomeBinding
import com.monarc.musclemate.ui.home.adapters.WorkoutListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
            homeViewModel.addNewWorkoutPlan()
        }
    }

    private fun subscribeToObservables() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.workoutPlans.collectLatest {
                it.let {
                    workoutListAdapter.submitList(it.toList())
                }
            }
        }


//            homeViewModel.workoutPlans.observe(viewLifecycleOwner) {
//                it.let {
//                    workoutListAdapter.submitList(it.toList())
//                }
//            }
    }

    private fun onWorkoutPlanItemClick(workoutPlan: WorkoutPlan) {
        homeViewModel.deleteWorkoutPlan(workoutPlan)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}