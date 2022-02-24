package com.monarc.musclemate.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.monarc.musclemate.data.entities.*

@Database(
    entities = [WorkoutPlan::class,
        WorkoutExercise::class,
        Exercise::class,
        ExerciseSet::class,
        CompletedExercise::class],
    version = 1
)
abstract class MuscleMateDatabase : RoomDatabase() {

    abstract val workoutPlanDao: WorkoutPlanDao
    abstract val workoutExerciseDao: WorkoutExerciseDao
    abstract val exerciseSetDao: ExerciseSetDao
    abstract val exerciseDao: ExerciseDao
    abstract val completedExerciseDao: CompletedExerciseDao

    companion object {
        const val DATABASE_NAME = "muscle_mate_db"
    }
}