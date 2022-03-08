package com.monarc.musclemate.ui.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.data.entities.relations.WorkoutRoutineExerciseWithExercise
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.WorkoutRoutineRepository
import com.monarc.musclemate.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    private val exerciseApiRepository: ExerciseApiRepository,
    private val workoutRoutineRepository: WorkoutRoutineRepository,
    private var savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val workoutId: Int? = savedStateHandle.get("workoutId")

    private val _workoutRoutine = MutableStateFlow(WorkoutRoutine(0, "", "", ""))
    val workoutRoutine = _workoutRoutine.asStateFlow()

    private val _exercises = MutableStateFlow(emptyList<WorkoutRoutineExerciseWithExercise>())
    val exercises = _exercises.asStateFlow()


    fun loadWorkoutRoutineData() {
        if (workoutId == WorkoutRoutine.NEW_WORKOUT_ROUTINE_ID)
            return

        loadWorkoutRoutine()
        getExercises()
    }

    fun loadWorkoutRoutine() {
        viewModelScope.launch {
            if(workoutId != null && workoutId != WorkoutRoutine.NEW_WORKOUT_ROUTINE_ID) {
                _workoutRoutine.value = workoutRoutineRepository.getWorkoutPlanById(workoutId)
            }
        }
    }

    fun getExercises() {
        viewModelScope.launch {
//            val result = exerciseApiRepository.getExercises()
            if (workoutId != null) {
                workoutRoutineRepository.getExercisesForWorkoutRoutine(workoutId).collect { items ->
                    _exercises.value = items
                }
            }
        }
    }

    fun saveWorkoutRoutine() {
        if (workoutId == null) {
            showToastMessage("workout routine is invalid")
        }

        if (workoutRoutine.value.title.isNullOrEmpty()) {
            showErrorMessage("Title for routine is required")
            return
        }

        viewModelScope.launch {
            when (workoutId) {
                WorkoutRoutine.NEW_WORKOUT_ROUTINE_ID -> {
                    workoutRoutineRepository.insertWorkoutPlan(workoutRoutine.value)
                }
                else -> {
                    workoutRoutineRepository.updateWorkoutPlan(workoutRoutine.value)
                }
            }
        }
    }

    fun deleteWorkoutRoutine() {
        if(workoutId == WorkoutRoutine.NEW_WORKOUT_ROUTINE_ID)
            return

        viewModelScope.launch {
            workoutRoutineRepository.deleteWorkoutRoutine(workoutRoutine.value)
            // TODO delete exercises, config, etc
        }
    }

}