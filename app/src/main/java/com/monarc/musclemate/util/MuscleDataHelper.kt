package com.monarc.musclemate.util

import com.monarc.musclemate.domain.models.Muscle

object MuscleDataHelper {

    fun getMuscle(id: Int): Muscle? {
        return if (id > muscles.size || id < 1) {
            null
        } else muscles[id-1]
    }

    fun getAllMuscles() = muscles


    private val muscles = listOf(
        Muscle(
            1,
            "Biceps brachii",
            "/static/images/muscles/main/muscle-1.svg",
            "/static/images/muscles/secondary/muscle-1.svg"
        ),
        Muscle(
            2,
            "Anterior deltoid",
            "/static/images/muscles/main/muscle-2.svg",
            "/static/images/muscles/secondary/muscle-2.svg"
        ),
        Muscle(
            3,
            "Serratus anterior",
            "/static/images/muscles/main/muscle-3.svg",
            "/static/images/muscles/secondary/muscle-3.svg"
        ),
        Muscle(
            4,
            "Pectoralis major",
            "/static/images/muscles/main/muscle-4.svg",
            "/static/images/muscles/secondary/muscle-4.svg"
        ),
        Muscle(
            5,
            "Triceps brachii",
            "/static/images/muscles/main/muscle-5.svg",
            "/static/images/muscles/secondary/muscle-5.svg"
        ),
        Muscle(
            6,
            "Rectus abdominis",
            "/static/images/muscles/main/muscle-6.svg",
            "/static/images/muscles/secondary/muscle-6.svg"
        ),
        Muscle(
            7,
            "Gastrocnemius",
            "/static/images/muscles/main/muscle-7.svg",
            "/static/images/muscles/secondary/muscle-7.svg"
        ),
        Muscle(
            8,
            "Gluteus maximus",
            "/static/images/muscles/main/muscle-8.svg",
            "/static/images/muscles/secondary/muscle-8.svg"
        ),
        Muscle(
            9,
            "Trapezius",
            "/static/images/muscles/main/muscle-9.svg",
            "/static/images/muscles/secondary/muscle-9.svg"
        ),
        Muscle(
            10,
            "Quadriceps femoris",
            "/static/images/muscles/main/muscle-10.svg",
            "/static/images/muscles/secondary/muscle-10.svg"
        ),
        Muscle(
            11,
            "Biceps femoris",
            "/static/images/muscles/main/muscle-11.svg",
            "/static/images/muscles/secondary/muscle-11.svg"
        ),
        Muscle(
            12,
            "Latissimus dorsi",
            "/static/images/muscles/main/muscle-12.svg",
            "/static/images/muscles/secondary/muscle-12.svg"
        ),
        Muscle(
            13,
            "Brachialis",
            "/static/images/muscles/main/muscle-13.svg",
            "/static/images/muscles/secondary/muscle-13.svg"
        ),
        Muscle(
            14,
            "Obliquus externus abdominis",
            "/static/images/muscles/main/muscle-14.svg",
            "/static/images/muscles/secondary/muscle-14.svg"
        ),
        Muscle(
            15,
            "Soleus",
            "/static/images/muscles/main/muscle-15.svg",
            "/static/images/muscles/secondary/muscle-15.svg"
        ),
    )
}