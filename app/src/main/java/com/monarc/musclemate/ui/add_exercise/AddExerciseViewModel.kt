package com.monarc.musclemate.ui.add_exercise

import androidx.lifecycle.viewModelScope
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.enums.ApiExerciseCategory
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.ui.BaseViewModel
import com.monarc.musclemate.util.MuscleDataHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val exerciseApiRepository: ExerciseApiRepository
) : BaseViewModel() {

    val muscleList = MuscleDataHelper.getAllMuscles()
    val exerciseCategoryList = ApiExerciseCategory.values()

    private var _exercises = MutableStateFlow(emptyList<Exercise>())
    val exercises = _exercises.asStateFlow()

    fun fetchExercisesFromApi() {
        viewModelScope.launch {
            when (val result = exerciseApiRepository.getExercises()) {
                is Resource.Success -> {
                    result.data?.let {
                        _exercises.value = result.data.results
                    }
                }
                is Resource.Error -> {
                    showErrorMessage(result.message!!)
                }
                is Resource.Loading -> {
                    showToastMessage("Downloading exercises")
                }
            }
        }
    }
}