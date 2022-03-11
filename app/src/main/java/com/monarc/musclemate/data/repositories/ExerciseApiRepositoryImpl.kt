package com.monarc.musclemate.data.repositories

import android.util.Log
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.ExerciseApi
import com.monarc.musclemate.data.remote.dto.BaseExerciseDto
import com.monarc.musclemate.data.remote.dto.ExerciseInfoDto
import com.monarc.musclemate.data.remote.responses.ExerciseApiResponse
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import javax.inject.Inject

class ExerciseApiRepositoryImpl @Inject constructor(
    private val exerciseApi: ExerciseApi
) : ExerciseApiRepository {

    override suspend fun getExercises(): Resource<ExerciseApiResponse<Exercise>> {
        return try {
            val response = exerciseApi.getExercises(ApiConstants.API_LANGUAGE_EN)

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), result)
            }
        } catch (e: Exception) {
            Log.e("ExerciseApiRepository",e.localizedMessage.toString())
            Resource.Error(e.localizedMessage ?: "An error occurred")
        }
    }

    override suspend fun getBaseExercises(): Resource<ExerciseApiResponse<BaseExerciseDto>> {
        return try {
            val response = exerciseApi.getBaseExercises(ApiConstants.API_LANGUAGE_EN)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), result)
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An error occurred")
        }
    }

    override suspend fun getExerciseInfoById(id: Int): Resource<ExerciseInfoDto> {
        return try {
            val response = exerciseApi.getExerciseInfoForId(id)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message(), result)
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An error occurred")
        }
    }
}