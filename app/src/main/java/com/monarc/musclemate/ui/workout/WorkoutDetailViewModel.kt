package com.monarc.musclemate.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.WorkoutRoutineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    private val exerciseApiRepository: ExerciseApiRepository,
    private val workoutRoutineRepository: WorkoutRoutineRepository
) : ViewModel() {


    private val _workoutRoutine = MutableStateFlow<WorkoutRoutine>(WorkoutRoutine(0,"","",""))
    val workoutRoutine = _workoutRoutine.asStateFlow()

    private val _exercises = MutableStateFlow(emptyList<Exercise>())
    val exercises = _exercises.asStateFlow()

    fun getWorkoutRoutine(id: Int) {
        if(id == WorkoutRoutine.NEW_WORKOUT_ROUTINE_ID)
            return

        viewModelScope.launch {
            _workoutRoutine.value = workoutRoutineRepository.getWorkoutPlanById(id)
        }
    }

    fun getExercises() {
        viewModelScope.launch {
            val result = exerciseApiRepository.getExercises()

            when(result) {
                is Resource.Success -> {
                    val data = result.data?.results?.map { from -> Exercise(from.id, from.name, null) }
                }
            }
        }
    }

    fun saveNewWorkoutRoutine() {

    }

}