package com.monarc.musclemate.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.monarc.musclemate.data.Resource
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.domain.repositories.ExerciseApiRepository
import com.monarc.musclemate.domain.repositories.ExerciseRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.lang.Exception

@HiltWorker
class DownloadExercisesWorker @AssistedInject constructor(
    private val exerciseApiRepository: ExerciseApiRepository,
    private val exerciseRepository: ExerciseRepository,
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    companion object {
        const val REQUEST_TAG = "DownloadExercisesWorkerTag"
    }

    override suspend fun doWork(): Result {
        Log.i("DownloadExercisesWorker", "Running download exercise worker")

        return try {
            when (val result = exerciseApiRepository.getExercises()) {
                is Resource.Success -> {
                    result.data?.let {
                        val exercises = it.results.map { item ->
                            Exercise(
                                0,
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

                        return Result.success()
                    }
                }
                is Resource.Error -> {
                    Log.e("DownloadExercisesWorker", result.message.toString())
                    return Result.failure()
                }
                else ->  Result.failure()
            }
            return Result.retry()
        } catch (e: Exception) {
            Log.e("DownloadExercisesWorker", e.localizedMessage)
            return Result.failure()
        }
    }
}