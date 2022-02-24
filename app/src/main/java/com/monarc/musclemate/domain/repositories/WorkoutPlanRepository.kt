package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.entities.WorkoutPlan
import kotlinx.coroutines.flow.Flow

interface WorkoutPlanRepository {

    fun getWorkoutPlans(): Flow<List<WorkoutPlan>>

    suspend fun getWorkoutPlanById(id: Int) : WorkoutPlan
    suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlan)
    suspend fun deleteWorkoutPlan(workoutPlan: WorkoutPlan)
    suspend fun updateWorkoutPlan(workoutPlan: WorkoutPlan)
}