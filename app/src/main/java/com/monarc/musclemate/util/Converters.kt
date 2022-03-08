package com.monarc.musclemate.util

import androidx.room.TypeConverter

class Converters {

}

class IntListConverter {
    @TypeConverter
    fun fromString(listString: String): List<Int> {
        return listString.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun toString(list: List<Int>): String {
        return list.joinToString(separator = ",")
    }
}