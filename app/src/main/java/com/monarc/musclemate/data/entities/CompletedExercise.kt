package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * CompletedExercise represents a completed exercise. It has a list of ExerciseSets
 */
@Entity(tableName = "completed_exercise")
data class CompletedExercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "workout_id")
    val workoutId: Int,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,
    @ColumnInfo(name = "date")
    val date: String
)
