package com.monarc.musclemate.domain.repositories

import com.monarc.musclemate.data.Resource

interface ExerciseApiRepository {

    suspend fun getExercises() : Resource<String>
}