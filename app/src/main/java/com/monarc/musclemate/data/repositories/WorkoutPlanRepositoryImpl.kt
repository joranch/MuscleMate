package com.monarc.musclemate.data.repositories

import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.data_source.WorkoutPlanDao
import com.monarc.musclemate.data.entities.WorkoutPlan
import com.monarc.musclemate.domain.repositories.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutPlanRepositoryImpl @Inject constructor(database: MuscleMateDatabase) : WorkoutPlanRepository {
    private val workoutDao = database.workoutPlanDao

    override fun getWorkoutPlans(): Flow<List<WorkoutPlan>> {
        return workoutDao.getAllWorkoutPlans()
    }

    override suspend fun getWorkoutPlanById(id: Int): WorkoutPlan {
        return workoutDao.getWorkoutPlanById(id)
    }

    override suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlan) {
        workoutDao.insert(workoutPlan)
    }

    override suspend fun deleteWorkoutPlan(workoutPlan: WorkoutPlan) {
        workoutDao.delete(workoutPlan)
        // TODO - Clean up other tables
    }

    override suspend fun updateWorkoutPlan(workoutPlan: WorkoutPlan) {
        workoutDao.update(workoutPlan)
    }
}