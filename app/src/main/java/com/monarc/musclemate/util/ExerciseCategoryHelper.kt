package com.monarc.musclemate.util

import com.monarc.musclemate.data.enums.ExerciseCategory

class ExerciseCategoryHelper {
    private val categories = ExerciseCategory.values()

    fun getCategoryById(id: Int) : ExerciseCategory {
        return categories.find { category -> category.id == id } ?: ExerciseCategory.None
    }

}