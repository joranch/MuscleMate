package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_plan", )
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
)

/* Models
WorkoutPlan
- Id
- Title
- Description
- Title
- List<ExcerciseConfiguration>

ExcerciseConfiguration
- Id
- WorkoutPlanId
- ExcerciseId
- NumOfSet
- NumOfReps
- AutoIncrementWeight

Workout
- Id
- Date
- WorkoutDayId
- List<CompletedExcercise>

CompletedExercise
- Id
- WorkoutId
- ExerciseId
- Date
- List<ExerciseSet>

ExerciseSet
- Id
- CompletedExcerciseId
- Rep
- Weight
- WeightUnit

Exercise
- Id
- ApiId
- Name
- Description */