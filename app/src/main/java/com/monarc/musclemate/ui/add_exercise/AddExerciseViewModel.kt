package com.monarc.musclemate.ui.add_exercise

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.monarc.musclemate.MuscleMateApplication
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.enums.ExerciseCategory
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.ExerciseRepository
import com.monarc.musclemate.ui.BaseViewModel
import com.monarc.musclemate.util.DispatcherProvider
import com.monarc.musclemate.util.MuscleDataHelper
import com.monarc.musclemate.workers.DownloadExercisesWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val exerciseApiRepository: ExerciseApiRepository,
    private val exerciseRepository: ExerciseRepository,
    private val dispatchers: DispatcherProvider
) : BaseViewModel() {

    sealed class ApiEvent {
        class Success(val resultText: String): ApiEvent()
        class Failure(val errorText: String): ApiEvent()
        object Loading : ApiEvent()
        object Empty : ApiEvent()
    }

    internal var workerInfo: LiveData<MutableList<WorkInfo>>
    private val workManager = WorkManager.getInstance(application)

    private val _apiEvent = MutableStateFlow<ApiEvent>(ApiEvent.Empty)
    val apiEvent: StateFlow<ApiEvent> = _apiEvent

    val muscleList = MuscleDataHelper().getAllMuscles()
    val exerciseCategoryList = ExerciseCategory.values()

    private var _exercises = MutableStateFlow(emptyList<Exercise>())
    val exercises = _exercises


    init {
        viewModelScope.launch {
            exerciseRepository.getAlExercises().collect { items ->
                _exercises.value = items
            }
        }

        workerInfo = workManager.getWorkInfosByTagLiveData(DownloadExercisesWorker.REQUEST_TAG)
//        fetchExercisesFromApi()
    }

    suspend fun checkHasExercises() : Boolean {
            val hasExercises = exerciseRepository.hasExercisesInDatabase()
        Log.i("AddExerciseViewModel", "Has exercises: $hasExercises")

        return hasExercises
//            if(!hasExercises) {
//                val workinfo = workManager.getWorkInfosByTag(DownloadExercisesWorker.REQUEST_TAG)
//                if(workinfo?.isDone) {
//                    fetchExercisesFromApi()
//                }
//            }
    }

    fun fetchExercisesFromApi() {
        viewModelScope.launch(dispatchers.io) {
            _apiEvent.value = ApiEvent.Loading
            when (val result = exerciseApiRepository.getExercises()) {
                is Resource.Success -> {
//                    result.data?.let {
//                        _exercises.value = result.data.results
//                    }
                    showToastMessage("Download success")

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

    fun setExercisesLoading(isLoading: Boolean) {
        if(isLoading) {
            _apiEvent.value = ApiEvent.Loading
        }
        else {
            _apiEvent.value = ApiEvent.Empty
        }
    }
}