package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * WorkoutExercise is a class that configures sets and reps for a single Exercise
 */
@Entity(tableName = "workout_exercise")
data class WorkoutExercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "workout_plan_id")
    val workoutPlanId: Int,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,
    @ColumnInfo(name = "number_of_sets")
    val numOfSets: Int = 3,
    @ColumnInfo(name = "number_of_reps")
    val numOfReps: Int = 8,
    @ColumnInfo(name = "auto_add_weight")
    val autoIncrementWeight: Boolean = false
)
