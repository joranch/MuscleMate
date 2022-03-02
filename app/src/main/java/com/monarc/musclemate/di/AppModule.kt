package com.monarc.musclemate.di

import android.app.Application
import androidx.room.Room
import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.ExerciseApi
import com.monarc.musclemate.data.repositories.ExerciseApiRepositoryImpl
import com.monarc.musclemate.data.repositories.WorkoutPlanRepositoryImpl
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.WorkoutPlanRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMuscleMateDatabase(app: Application): MuscleMateDatabase {
        return Room.databaseBuilder(
            app,
            MuscleMateDatabase::class.java,
            MuscleMateDatabase.DATABASE_NAME
        ).build()
    }


    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideExerciseApi(moshi: Moshi): ExerciseApi = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(ApiConstants.BASE_EXERCISE_URL)
        .build()
        .create(ExerciseApi::class.java)

    @Singleton
    @Provides
    fun provideExerciseApiRepository(api: ExerciseApi): ExerciseApiRepository = ExerciseApiRepositoryImpl(api)

    @Module
    @InstallIn(SingletonComponent::class)
    internal abstract class DependenciesBindings {
        @Singleton
        @Binds
        abstract fun bindWorkoutPlanRepository(
            workoutPlanRepositoryImpl: WorkoutPlanRepositoryImpl): WorkoutPlanRepository
    }
}