package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.WorkoutRoutine
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutPlanDao {

    @Query("SELECT * FROM workout_routine WHERE id = :id")
    suspend fun getWorkoutPlanById(id: Int): WorkoutRoutine

    @Query("SELECT * FROM workout_routine")
    fun getAllWorkoutPlans(): Flow<List<WorkoutRoutine>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutRoutine: WorkoutRoutine)

    @Update
    suspend fun update(workoutRoutine: WorkoutRoutine)

    @Delete
    suspend fun delete(workoutRoutine: WorkoutRoutine)
}
