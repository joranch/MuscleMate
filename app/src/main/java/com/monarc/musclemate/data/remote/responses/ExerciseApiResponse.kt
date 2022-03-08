package com.monarc.musclemate.data.remote.responses

class ExerciseApiResponse<T>(
    val count: Int = 0,
    val next: String?,
    val previous: String?,
    val results: List<T> = emptyList()
) {
}