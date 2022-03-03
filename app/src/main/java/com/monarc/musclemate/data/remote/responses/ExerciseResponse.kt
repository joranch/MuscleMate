package com.monarc.musclemate.data.remote.responses

import com.monarc.musclemate.data.remote.dto.ExerciseDto

class ExerciseResponse(
    val count: Int = 0,
    val next: String?,
    val previous: String?,
    val results: List<ExerciseDto> = emptyList()
) {

}