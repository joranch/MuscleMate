package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.remote.dto.BaseExerciseDto
import com.monarc.musclemate.data.remote.dto.ExerciseDto
import com.monarc.musclemate.data.remote.dto.ExerciseInfoDto
import com.monarc.musclemate.data.remote.responses.ExerciseApiResponse

interface ExerciseApiRepository {

    suspend fun getExercises() : Resource<ExerciseApiResponse<ExerciseDto>> // TODO: Refactor to flow
    suspend fun downloadExercises(): Boolean
    suspend fun getBaseExercises() : Resource<ExerciseApiResponse<BaseExerciseDto>>
    suspend fun getExerciseInfoById(id: Int) : Resource<ExerciseInfoDto>
}