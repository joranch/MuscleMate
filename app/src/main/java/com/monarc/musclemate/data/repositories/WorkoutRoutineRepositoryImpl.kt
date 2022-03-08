package com.monarc.musclemate.data.repositories

import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.data.entities.relations.WorkoutRoutineExerciseWithExercise
import com.monarc.musclemate.domain.repositories.WorkoutRoutineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutRoutineRepositoryImpl @Inject constructor(database: MuscleMateDatabase) :
    WorkoutRoutineRepository {
    private val workoutDao = database.workoutRoutineDao
    private val exerciseDao = database.exerciseDao
    private val exerciseConfigurationDao = database.exerciseConfigurationDao
    private val workoutRoutineExercisesDao = database.workoutRoutineExerciseDao

    override fun getWorkoutPlans(): Flow<List<WorkoutRoutine>> {
        return workoutDao.getAllWorkoutPlans()
    }

    override fun getExercisesForWorkoutRoutine(workoutRoutineId: Int): Flow<List<WorkoutRoutineExerciseWithExercise>> {
        return workoutRoutineExercisesDao.getExercisesForWorkoutRoutine(workoutRoutineId)
    }

    override suspend fun getWorkoutPlanById(id: Int): WorkoutRoutine {
        return workoutDao.getWorkoutPlanById(id)
    }

    override suspend fun insertWorkoutPlan(workoutRoutine: WorkoutRoutine) {
        workoutDao.insert(workoutRoutine)
    }

    override suspend fun deleteWorkoutRoutine(workoutRoutine: WorkoutRoutine) {
        workoutDao.delete(workoutRoutine)
        // TODO - Clean up other tables
    }

    override suspend fun updateWorkoutPlan(workoutRoutine: WorkoutRoutine) {
        workoutDao.update(workoutRoutine)
    }
}