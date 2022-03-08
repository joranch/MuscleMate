package com.monarc.musclemate.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monarc.musclemate.data.entities.*
import com.monarc.musclemate.util.IntListConverter

@Database(
    entities = [WorkoutRoutine::class,
        ExerciseConfiguration::class,
        Exercise::class,
        ExerciseSet::class,
        CompletedExercise::class,
        WorkoutRoutineExercise::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntListConverter::class)
abstract class MuscleMateDatabase : RoomDatabase() {

    abstract val workoutRoutineDao: WorkoutRoutineDao
    abstract val exerciseConfigurationDao: ExerciseConfigurationDao
    abstract val exerciseSetDao: ExerciseSetDao
    abstract val exerciseDao: ExerciseDao
    abstract val completedExerciseDao: CompletedExerciseDao
    abstract val workoutRoutineExerciseDao: WorkoutRoutineExercisesDao


    companion object {
        const val DATABASE_NAME = "muscle_mate_db"
    }
}