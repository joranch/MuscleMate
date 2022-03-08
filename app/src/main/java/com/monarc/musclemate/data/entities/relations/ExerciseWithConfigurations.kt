package com.monarc.musclemate.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.data.entities.ExerciseConfiguration

data class ExerciseWithConfigurations(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "id",
        entityColumn = "exercise_id"
    )
    val exerciseConfiguration: List<ExerciseConfiguration> = emptyList()
)