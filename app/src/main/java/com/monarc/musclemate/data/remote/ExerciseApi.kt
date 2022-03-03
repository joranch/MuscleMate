package com.monarc.musclemate.data.remote

import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.dto.ExerciseDto
import com.monarc.musclemate.data.remote.responses.ExerciseResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
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
    ) : Response<ExerciseResponse>

    @GET("exercise/")
    suspend fun getExerciseForId(
        @Query("language") language: Int,
        @Query("id") id: Int
    ) : Response<ExerciseResponse>

    @GET("exercisebaseinfo/")
    suspend fun getBaseExercises(
        @Query("language") language: Int,
        @Query("limit") limit: Int = 500
    ) : Response<BaseExerciseResponse>
}