package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.CompletedExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedExerciseDao {

    @Query("SELECT * FROM completed_exercise WHERE exercise_id = :exerciseId order by date DESC")
    fun getCompletedExercisesOfExercise(exerciseId: Int): Flow<List<CompletedExercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(completedExercise: CompletedExercise)

    @Delete
    suspend fun delete(completedExercise: CompletedExercise)

    @Update
    suspend fun update(completedExercise: CompletedExercise)
}
