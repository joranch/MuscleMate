package com.monarc.musclemate.di

import android.app.Application
import androidx.room.Room
import com.monarc.musclemate.data.data_source.MuscleMateDatabase
import com.monarc.musclemate.data.repositories.WorkoutPlanRepositoryImpl
import com.monarc.musclemate.domain.repositories.WorkoutPlanRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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


    @Module
    @InstallIn(SingletonComponent::class)
    internal abstract class DependenciesBindings {
        @Singleton
        @Binds
        abstract fun bindWorkoutPlanRepository(
            workoutPlanRepositoryImpl: WorkoutPlanRepositoryImpl): WorkoutPlanRepository
    }
}