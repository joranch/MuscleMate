package com.monarc.musclemate.data.repositories

import com.monarc.musclemate.data.data_source.ExerciseDao
import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.domain.repositories.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ExerciseRepositoryImpl @Inject constructor(private val database: MuscleMateDatabase)
    : ExerciseRepository{
    override fun getAlExercises(): Flow<List<Exercise>> {
        return database.exerciseDao.getAllExercises()
    }

    override suspend fun hasExercisesInDatabase(): Boolean {
        return database.exerciseDao.getExerciseCount() > 100
    }

    override suspend fun getExercise(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(exercises: List<Exercise>) {
        database.exerciseDao.insertAll(exercises)
    }
}