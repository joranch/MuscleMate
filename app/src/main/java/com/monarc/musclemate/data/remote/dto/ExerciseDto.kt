package com.monarc.musclemate.data.remote.dto

class ExerciseDto(
    val id: Int,
    val uuid: String,
    val name: String,
    val exercise_base: Int,
    val description: String,
    val category: Int,
    val muscles: List<Int> = emptyList(),
    val muscles_secondary: List<Int>  = emptyList(),
    val equipment: List<Int>  = emptyList(),
    val variations: List<Int>  = emptyList(),
) {
}