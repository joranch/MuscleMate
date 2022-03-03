package com.monarc.musclemate.data.remote.dto

import com.monarc.musclemate.data.enums.ApiExerciseCategory
import com.monarc.musclemate.data.enums.EquipmentType
import com.monarc.musclemate.domain.models.Muscle

class BaseExerciseDto(
    val id: Int,
    val uuid: String,
    val name: String,
    val exercise_base: Int,
    val description: String,
    val category: List<ApiExerciseCategory>,
    val muscles: List<Muscle> = emptyList(),
    val muscles_secondary: List<Muscle>  = emptyList(),
    val equipment: List<EquipmentType>  = emptyList(),
    val images: List<String> = emptyList(),
    val exercises: List<ExerciseDto> = emptyList(),
    val variations: List<Int>  = emptyList(),
) {
}