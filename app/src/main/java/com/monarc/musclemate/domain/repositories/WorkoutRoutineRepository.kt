package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.entities.WorkoutRoutine
import kotlinx.coroutines.flow.Flow

interface WorkoutRoutineRepository {

    fun getWorkoutPlans(): Flow<List<WorkoutRoutine>>

    suspend fun getWorkoutPlanById(id: Int) : WorkoutRoutine
    suspend fun insertWorkoutPlan(workoutRoutine: WorkoutRoutine)
    suspend fun deleteWorkoutPlan(workoutRoutine: WorkoutRoutine)
    suspend fun updateWorkoutPlan(workoutRoutine: WorkoutRoutine)
}