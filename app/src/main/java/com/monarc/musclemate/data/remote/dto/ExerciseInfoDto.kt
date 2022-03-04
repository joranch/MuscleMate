package com.monarc.musclemate.data.remote.dto

import com.monarc.musclemate.data.enums.EquipmentType
import com.monarc.musclemate.domain.models.Muscle

data class ExerciseInfoDto(
    val id: Int,
    val uuid: String,
    val name: String,
    val exercise_base: Int?,
    val description: String,
    val category: CategoryDto,
    val muscles: List<Muscle> = emptyList(),
    val muscles_secondary: List<Muscle>  = emptyList(),
    val equipment: List<EquipmentDto>  = emptyList(),
    val variations: List<Int>  = emptyList(),
    val images: List<ImageDto> = emptyList()
)