package com.monarc.musclemate.data.remote.dto

data class ExerciseDto(
    val id: Int,
    val uuid: String,
    val name: String,
    val exerciseBase: Int?,
    val description: String = "",
    val category: Int?,
    val muscles: List<Int> = emptyList(),
    val musclesSecondary: List<Int>  = emptyList(),
    val equipment: List<Int>  = emptyList(),
    val variations: List<Int>  = emptyList()
)