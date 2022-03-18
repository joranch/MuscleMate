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

    @Query("SELECT COUNT(*) from exercise")
    suspend fun getExerciseCount() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(exercises: List<Exercise>)
}
