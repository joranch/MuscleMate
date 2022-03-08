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
    @ColumnInfo(name = "exercise_base")
    val exerciseBase: Int?,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "category")
    val category: Int?,
    @ColumnInfo(name = "target_muscle")
    val muscles: List<Int> = emptyList(),
    @ColumnInfo(name = "target_muscle_secondary")
    val musclesSecondary: List<Int>  = emptyList(),
    @ColumnInfo(name = "equipment")
    val equipment: List<Int>  = emptyList(),
    @ColumnInfo(name = "variations")
    val variations: List<Int>  = emptyList()
)
