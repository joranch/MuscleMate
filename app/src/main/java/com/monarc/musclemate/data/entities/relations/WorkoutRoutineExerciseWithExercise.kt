package com.monarc.musclemate.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.entities.WorkoutRoutineExercise

data class WorkoutRoutineExerciseWithExercise(
    @Embedded val workoutRoutineExercise: WorkoutRoutineExercise,
    @Relation(
        parentColumn = "exercise_id",
        entityColumn = "id"
    )
    val exercises: Exercise
)