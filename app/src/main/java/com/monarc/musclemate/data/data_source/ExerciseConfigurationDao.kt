package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.ExerciseConfiguration
import com.monarc.musclemate.data.entities.relations.ExerciseWithConfigurations
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseConfigurationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exerciseConfiguration: ExerciseConfiguration)

    @Update
    suspend fun update(exerciseConfiguration: ExerciseConfiguration)

    @Delete
    suspend fun delete(exerciseConfiguration: ExerciseConfiguration)

    @Query("DELETE FROM exercise_configuration WHERE workout_plan_id = :workoutPlanId")
    suspend fun deleteAllForWorkoutPlan(workoutPlanId: Int)
}
