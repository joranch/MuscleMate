package com.monarc.musclemate.data.repositories

import android.util.Log
import androidx.work.ListenableWorker
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.ExerciseApi
import com.monarc.musclemate.data.remote.dto.BaseExerciseDto
import com.monarc.musclemate.data.remote.dto.ExerciseDto
import com.monarc.musclemate.data.remote.dto.ExerciseInfoDto
import com.monarc.musclemate.data.remote.responses.ExerciseApiResponse
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.ExerciseRepository
import javax.inject.Inject

class ExerciseApiRepositoryImpl @Inject constructor(
    private val exerciseApi: ExerciseApi,
    private val exerciseRepository: ExerciseRepository,
) : ExerciseApiRepository {

    override suspend fun getExercises(): Resource<ExerciseApiResponse<ExerciseDto>> {
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

    override suspend fun downloadExercises() {
        when (val result = getExercises()) {
            is Resource.Success -> {
                result.data?.let {
                    val exercises = it.results.map { item ->
                        Exercise(
                            item.id,
                            item.name,
                            item.id,
                            item.exerciseBase,
                            item.description,
                            item.category,
                            item.muscles,
                            item.musclesSecondary,
                            item.equipment,
                            item.variations
                        )
                    }
                    exerciseRepository.insertAll(exercises)
                }
            }
            is Resource.Error -> {
                Log.e("DownloadExercisesWorker", result.message.toString())
            }
            else -> {}
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