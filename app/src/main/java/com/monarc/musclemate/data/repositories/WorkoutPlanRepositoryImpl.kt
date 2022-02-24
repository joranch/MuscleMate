package com.monarc.musclemate.data.repositories

import com.monarc.musclemate.data.data_source.WorkoutPlanDao
import com.monarc.musclemate.data.entities.WorkoutPlan
import com.monarc.musclemate.domain.repositories.WorkoutPlanRepository
import kotlinx.coroutines.flow.Flow

class WorkoutPlanRepositoryImpl(private val dao: WorkoutPlanDao) : WorkoutPlanRepository {
    override fun getWorkoutPlans(): Flow<List<WorkoutPlan>> {
        return dao.getAllWorkoutPlans()
    }

    override suspend fun getWorkoutPlanById(id: Int): WorkoutPlan {
        return dao.getWorkoutPlanById(id)
    }

    override suspend fun insertWorkoutPlan(workoutPlan: WorkoutPlan) {
        dao.insert(workoutPlan)
    }

    override suspend fun deleteWorkoutPlan(workoutPlan: WorkoutPlan) {
        dao.delete(workoutPlan)
        // TODO - Clean up other tables
    }

    override suspend fun updateWorkoutPlan(workoutPlan: WorkoutPlan) {
        dao.update(workoutPlan)
    }
}