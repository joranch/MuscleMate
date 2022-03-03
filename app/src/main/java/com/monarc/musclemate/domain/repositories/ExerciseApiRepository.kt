package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.remote.dto.ExerciseDto
import com.monarc.musclemate.data.remote.responses.ExerciseResponse

interface ExerciseApiRepository {

    suspend fun getExercises() : Resource<ExerciseResponse>
}