package com.monarc.musclemate.domain.models

data class Muscle(
    val id: Int,
    val name: String,
    val imageUrlMain: String?,
    val imageUrlSecondary: String?
) {
}