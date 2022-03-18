package com.monarc.musclemate.di

import android.app.Application
import androidx.room.Room
import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.enums.ApiConstants
import com.monarc.musclemate.data.remote.ExerciseApi
import com.monarc.musclemate.data.repositories.ExerciseApiRepositoryImpl
import com.monarc.musclemate.data.repositories.ExerciseRepositoryImpl
import com.monarc.musclemate.data.repositories.WorkoutRoutineRepositoryImpl
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.ExerciseRepository
import com.monarc.musclemate.domain.repositories.WorkoutRoutineRepository
import com.monarc.musclemate.util.DispatcherProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    fun provideWorkoutRepository(db: MuscleMateDatabase): WorkoutRoutineRepository =
        WorkoutRoutineRepositoryImpl(db)

    @Singleton
    @Provides
    fun provideExerciseRepository(db: MuscleMateDatabase): ExerciseRepository =
        ExerciseRepositoryImpl(db)

    @Singleton
    @Provides
    fun provideExerciseApiRepository(
        api: ExerciseApi,
        exerciseRepository: ExerciseRepository
    ): ExerciseApiRepository = ExerciseApiRepositoryImpl(api, exerciseRepository)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
//    @Module
//    @InstallIn(SingletonComponent::class)
//    internal abstract class DependenciesBindings {
//        @Singleton
//        @Binds
//        abstract fun bindWorkoutPlanRepository(
//            workoutPlanRepositoryImpl: WorkoutRoutineRepositoryImpl): WorkoutRoutineRepository
//    }
}