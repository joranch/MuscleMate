package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.data.entities.relations.WorkoutRoutineExerciseWithExercise
import kotlinx.coroutines.flow.Flow

interface WorkoutRoutineRepository {

    fun getWorkoutPlans(): Flow<List<WorkoutRoutine>>
    fun getExercisesForWorkoutRoutine(workoutRoutineId: Int): Flow<List<WorkoutRoutineExerciseWithExercise>>
    suspend fun getWorkoutPlanById(id: Int) : WorkoutRoutine
    suspend fun insertWorkoutPlan(workoutRoutine: WorkoutRoutine)
    suspend fun deleteWorkoutPlan(workoutRoutine: WorkoutRoutine)
    suspend fun updateWorkoutPlan(workoutRoutine: WorkoutRoutine)
}