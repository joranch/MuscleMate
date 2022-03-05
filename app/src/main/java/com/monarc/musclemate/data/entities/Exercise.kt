package com.monarc.musclemate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val localId: Int,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "api_id")
    val apiId: String?,

    val exerciseBase: Int?,
    val description: String,
    val category: Int?,
)
//
//data class ExerciseDto(
//    val id: Int,
//    val uuid: String,
//    val name: String,
//    val exerciseBase: Int?,
//    val description: String,
//    val category: Int?,
//    val muscles: List<Int> = emptyList(),
//    val musclesSecondary: List<Int>  = emptyList(),
//    val equipment: List<Int>  = emptyList(),
//    val variations: List<Int>  = emptyList()
//)
