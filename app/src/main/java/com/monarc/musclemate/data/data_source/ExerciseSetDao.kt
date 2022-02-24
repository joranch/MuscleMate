package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.ExerciseSet
import com.monarc.musclemate.data.entities.WorkoutExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseSetDao {

    @Query("SELECT * FROM exercise_set WHERE completed_exercise_id = :completedExerciseId")
    suspend fun getSetsForExercise(completedExerciseId: Int): List<ExerciseSet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exerciseSet: ExerciseSet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exerciseSets: List<ExerciseSet>)

    @Update
    suspend fun update(exerciseSet: ExerciseSet)

    @Delete
    suspend fun delete(exerciseSet: ExerciseSet)

    @Query("DELETE FROM exercise_set WHERE completed_exercise_id = :completedExerciseId")
    suspend fun deleteAllForCompletedExercise(completedExerciseId: Int)
}
