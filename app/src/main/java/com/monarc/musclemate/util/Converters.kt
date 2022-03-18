package com.monarc.musclemate.util

import androidx.room.TypeConverter

class Converters {

}

class IntListConverter {
    @TypeConverter
    fun fromString(listString: String): List<Int> {
        if(listString.length <= 2)
            return emptyList()

        return listString.split(",").map { value ->
            value.toInt()
        }
    }

    @TypeConverter
    fun toString(list: List<Int>): String {
        return list.joinToString(separator = ",")
    }
}