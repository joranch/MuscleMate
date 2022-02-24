package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.WorkoutPlan
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutPlanDao {

    @Query("SELECT * FROM workout_plan WHERE id = :id")
    suspend fun getWorkoutPlan(id: Int): WorkoutPlan

    @Query("SELECT * FROM workout_plan")
    suspend fun getAllWorkoutPlans(): Flow<List<WorkoutPlan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutPlan: WorkoutPlan)

    @Update
    suspend fun update(workoutPlan: WorkoutPlan)

    @Delete
    suspend fun delete(workoutPlan: WorkoutPlan)
}
