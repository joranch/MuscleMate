package com.monarc.musclemate.data.repositories

import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.ExerciseApi
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.util.Status
import javax.inject.Inject

class ExerciseApiRepositoryImpl @Inject constructor(
    private val exerciseApi: ExerciseApi
) : ExerciseApiRepository {

    override suspend fun getExercises(): Resource<String> {
        return try {
            val response = exerciseApi.getExercises(ApiConstants.DEFAULT_EXERCISE_LANGUAGE)

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource(Status.SUCCESS, result, null)
            } else {
                Resource(Status.ERROR, result, response.message())

            }
        } catch (e: Exception) {
            Resource(Status.ERROR, null, e.localizedMessage ?: "An error occured")

        }
    }
}