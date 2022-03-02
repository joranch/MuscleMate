package com.monarc.musclemate.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
        private val exerciseApiRepository: ExerciseApiRepository) : ViewModel() {

    fun getExercises() {
            viewModelScope.launch {
                    val result = exerciseApiRepository.getExercises()
                    val data = result.data
            }
    }

}