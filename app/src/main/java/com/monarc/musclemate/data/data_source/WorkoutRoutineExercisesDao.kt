package com.monarc.musclemate.data.data_source

import androidx.room.*
import com.monarc.musclemate.data.entities.WorkoutRoutineExercise
import com.monarc.musclemate.data.entities.relations.ExerciseWithConfigurations
import com.monarc.musclemate.data.entities.relations.WorkoutRoutineExerciseWithExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutRoutineExercisesDao {

//    @Query("SELECT * FROM workout_routine_exercise")
//    fun getExercisesForRoutine(workoutRoutineId: Int) : List<WorkoutRoutineExercise>

    @Transaction
    @Query("SELECT * FROM workout_routine_exercise WHERE workout_routine_id = :workoutRoutineId")
    fun getExercisesForWorkoutRoutine(workoutRoutineId: Int): Flow<List<WorkoutRoutineExerciseWithExercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutRoutineExercise: WorkoutRoutineExercise)

    @Update
    suspend fun update(workoutRoutineExercise: WorkoutRoutineExercise)

    @Delete
    suspend fun delete(workoutRoutineExercise: WorkoutRoutineExercise)
}