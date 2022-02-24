package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ExerciseSet is an object that represents a completed set of an exercise
 */
@Entity(tableName = "exercise_set")
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "completed_exercise_id")
    val completedExerciseId: Int,
    @ColumnInfo(name = "reps")
    val reps: Int,
    @ColumnInfo(name = "weight")
    val weight: Int
)
