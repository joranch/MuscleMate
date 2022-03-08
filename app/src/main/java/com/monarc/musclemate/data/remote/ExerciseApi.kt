package com.monarc.musclemate.data.remote

import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.dto.BaseExerciseDto
import com.monarc.musclemate.data.remote.dto.ExerciseInfoDto
import com.monarc.musclemate.data.remote.responses.ExerciseApiResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(ApiConstants.BASE_EXERCISE_URL)
    .build()


interface ExerciseApi {

    @GET("exercise/")
    suspend fun getExercises(
        @Query("language") language: Int,
        @Query("limit") limit: Int = 500
    ): Response<ExerciseApiResponse<Exercise>>

    @GET("exerciseinfo/{id}")
    suspend fun getExerciseInfoForId(
        @Path("id") id: Int
    ): Response<ExerciseInfoDto>

    @GET("exercisebaseinfo/")
    suspend fun getBaseExercises(
        @Query("language") language: Int,
        @Query("limit") limit: Int = 500
    ): Response<ExerciseApiResponse<BaseExerciseDto>>
}