package com.monarc.musclemate.ui.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import com.monarc.musclemate.data.entities.WorkoutPlan
import com.monarc.musclemate.domain.repositories.WorkoutPlanRepository
import com.monarc.musclemate.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseViewModel() {

    private val _workoutPlans = workoutPlanRepository.getWorkoutPlans().asLiveData()
    val workoutPlans = _workoutPlans

    fun addNewWorkoutPlan() {
        viewModelScope.launch {
            workoutPlanRepository.insertWorkoutPlan(
                WorkoutPlan(
                    title = "Upper body",
                    description = "Upper body day",
                    lastWorkoutDate = null
                )
            )
        }
    }

    fun deleteWorkoutPlan(workoutPlan: WorkoutPlan) {
        viewModelScope.launch {
            workoutPlanRepository.deleteWorkoutPlan(workoutPlan)
        }
    }

}