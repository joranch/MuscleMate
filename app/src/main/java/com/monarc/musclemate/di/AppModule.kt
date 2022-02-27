package com.monarc.musclemate.di

import android.app.Application
import androidx.room.Room
import com.monarc.musclemate.data.data_source.MuscleMateDatabase
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
}