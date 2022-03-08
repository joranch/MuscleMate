package com.monarc.musclemate.ui.home

import androidx.lifecycle.*
import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.domain.repositories.WorkoutRoutineRepository
import com.monarc.musclemate.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workoutRoutineRepository: WorkoutRoutineRepository,

) : BaseViewModel() {

    private val _workoutRoutines = MutableStateFlow(emptyList<WorkoutRoutine>()) //workoutRoutineRepository.getWorkoutPlans().asLiveData()
    val workoutRoutines = _workoutRoutines.asStateFlow()

    init {
        viewModelScope.launch {
            workoutRoutineRepository.getWorkoutPlans().collect { items ->
                _workoutRoutines.value = items
            }
        }
    }

    fun deleteWorkoutPlan(workoutRoutine: WorkoutRoutine) {
        viewModelScope.launch {
            workoutRoutineRepository.deleteWorkoutPlan(workoutRoutine)
        }
    }

}