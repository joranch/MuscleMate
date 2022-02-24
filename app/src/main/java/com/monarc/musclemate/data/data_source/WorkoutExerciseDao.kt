package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.WorkoutExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    @Query("SELECT * FROM workout_exercise WHERE workout_plan_id = :workoutPlanId")
    suspend fun getExercisesForWorkoutPlan(workoutPlanId: Int): Flow<List<WorkoutExercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutExercise: WorkoutExercise)

    @Update
    suspend fun update(workoutExercise: WorkoutExercise)

    @Delete
    suspend fun delete(workoutExercise: WorkoutExercise)

    @Query("DELETE FROM workout_exercise WHERE workout_plan_id = :workoutPlanId")
    suspend fun deleteAllForWorkoutPlan(workoutPlanId: Int)
}
