package com.monarc.musclemate.data.remote.dto

import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.domain.models.Muscle

class BaseExerciseDto(
    val id: Int,
    val uuid: String,
//    val name: String?,
//    val description: String,
    val category: CategoryDto,
    val muscles: List<Muscle> = emptyList(),
    val muscles_secondary: List<Muscle>  = emptyList(),
    val equipment: List<EquipmentDto>  = emptyList(),
    val images: List<ImageDto> = emptyList(),
    val exercises: List<Exercise> = emptyList(),
//    val variations: List<Int?>  = emptyList(),
) {
}