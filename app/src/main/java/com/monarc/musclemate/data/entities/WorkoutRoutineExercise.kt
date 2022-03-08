package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_routine_exercise")
data class WorkoutRoutineExercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "workout_routine_id")
    val workoutRoutineId: Int,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int
)
