package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.entities.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getAlExercises() : Flow<List<Exercise>>
    suspend fun hasExercisesInDatabase() : Boolean
    suspend fun getExercise(id: Int)
    suspend fun insertAll(exercises: List<Exercise>)
}