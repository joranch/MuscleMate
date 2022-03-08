package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_routine", )
data class WorkoutRoutine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "last_workout_date")
    var lastWorkoutDate: String?

) {

    companion object {
        const val NEW_WORKOUT_ROUTINE_ID = -1
    }
}
