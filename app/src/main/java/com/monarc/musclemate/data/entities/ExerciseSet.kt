package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_set")
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "completed_exercise_id")
    val completedExcerciseId: Int,
    @ColumnInfo(name = "reps")
    val reps: Int,
    @ColumnInfo(name = "weight")
    val weight: Int
)
