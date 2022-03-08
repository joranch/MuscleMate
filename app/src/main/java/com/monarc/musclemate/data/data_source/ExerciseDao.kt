package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.entities.relations.ExerciseWithConfigurations
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = :exerciseId")
    suspend fun getExercise(exerciseId: Int): Exercise

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)
}
