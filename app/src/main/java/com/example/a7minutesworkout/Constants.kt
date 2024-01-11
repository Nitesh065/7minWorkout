package com.example.a7minutesworkout

object Constants {
    fun defaultExercise(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1,
            "JumpingJacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)
        val abdominalCrunch = ExerciseModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(abdominalCrunch)

        val highKneesRunning = ExerciseModel(
            3,
            "High Knees Running In Place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciseList.add(highKneesRunning)

        val lunge = ExerciseModel(
            4,
            "Lunges",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            5,
            "Planks",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            6,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(pushUp)

        val pushUpRotation = ExerciseModel(
            7,
            "PushUp And Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciseList.add(pushUpRotation)

        val sidePlank = ExerciseModel(
            8,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(squat)

        val stepUpOnChair = ExerciseModel(
            10,
            "StepUp OnTo Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(stepUpOnChair)

        val tricepsDip = ExerciseModel(
            11,
            "Triceps Dip on Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseList.add(tricepsDip)

        val wallSit = ExerciseModel(
            12,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)

        return exerciseList
    }

}