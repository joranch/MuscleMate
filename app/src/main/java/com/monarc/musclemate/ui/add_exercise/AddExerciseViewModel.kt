package com.monarc.musclemate.ui.add_exercise

import androidx.lifecycle.viewModelScope
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.enums.ExerciseCategory
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.ui.BaseViewModel
import com.monarc.musclemate.util.DispatcherProvider
import com.monarc.musclemate.util.MuscleDataHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    private val exerciseApiRepository: ExerciseApiRepository,
    private val dispatchers: DispatcherProvider
) : BaseViewModel() {

    sealed class ApiEvent {
        class Success(val resultText: String): ApiEvent()
        class Failure(val errorText: String): ApiEvent()
        object Loading : ApiEvent()
        object Empty : ApiEvent()
    }

    private val _apiEvent = MutableStateFlow<ApiEvent>(ApiEvent.Empty)
    val apiEvent: StateFlow<ApiEvent> = _apiEvent

    val muscleList = MuscleDataHelper().getAllMuscles()
    val exerciseCategoryList = ExerciseCategory.values()

    private var _exercises = MutableStateFlow(emptyList<Exercise>())
    val exercises = _exercises.asStateFlow()

    fun fetchExercisesFromApi() {
        viewModelScope.launch(dispatchers.io) {
            _apiEvent.value = ApiEvent.Loading
            when (val result = exerciseApiRepository.getExercises()) {
                is Resource.Success -> {
                    result.data?.let {
                        _exercises.value = result.data.results
                    }
                    _apiEvent.value = ApiEvent.Success("Download finished")
                }
                is Resource.Error -> {
                    showErrorMessage(result.message!!)
                    _apiEvent.value = ApiEvent.Failure(result.message!!)
                }
                is Resource.Loading -> {
//                    _apiEvent.value = ApiEvent.Loading
                    showToastMessage("Downloading exercises")
                }
            }
        }
    }
}