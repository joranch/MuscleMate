package com.monarc.musclemate.ui.home

import androidx.lifecycle.*
import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.data_source.WorkoutPlanDao
import com.monarc.musclemate.data.entities.WorkoutPlan
import com.monarc.musclemate.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val muscleMateDatabase: MuscleMateDatabase
) : BaseViewModel() {

    private val _workoutPlans = MutableStateFlow<ArrayList<WorkoutPlan>>(ArrayList())
    val workoutPlans = _workoutPlans.asStateFlow()

    fun addNewWorkoutPlan() {
        viewModelScope.launch {
            val x = muscleMateDatabase.workoutPlanDao.getAllWorkoutPlans().asLiveData()

        }
        _workoutPlans.value.add(WorkoutPlan(1, "Upper body", "Upper body day",null))
    }

}