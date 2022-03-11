package com.monarc.musclemate.ui.add_exercise.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monarc.musclemate.data.entities.Exercise
import com.monarc.musclemate.databinding.ItemExerciseListBinding
import com.monarc.musclemate.util.ExerciseCategoryHelper

class ExerciseListAdapter(
    private val onItemClicked: (Exercise) -> Unit,
    private val onInfoClicked: (Exercise) -> Unit
) :
    ListAdapter<Exercise, ExerciseListAdapter.ExerciseViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position), onInfoClicked)
        holder.itemView.setOnClickListener { onItemClicked }
    }


    class ExerciseViewHolder(private var binding: ItemExerciseListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise, onInfoClicked: (Exercise) -> Unit) {
            binding.categoryText.text =
                exerciseCategoryHelper.getCategoryById(exercise.category ?: 0).name
            binding.exerciseNameText
            binding.seeInfoButton.setOnClickListener { onInfoClicked }
        }
    }

    companion object {
        private val exerciseCategoryHelper: ExerciseCategoryHelper = ExerciseCategoryHelper()

        private val DiffCallback = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(
                oldItem: Exercise,
                newItem: Exercise
            ): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.apiId == newItem.apiId
//                        && oldItem.localId == newItem.localId
            }

            override fun areContentsTheSame(
                oldItem: Exercise,
                newItem: Exercise
            ): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.name == newItem.name
                        && oldItem.description == newItem.description
                        && oldItem.equipment == newItem.equipment
                        && oldItem.category == newItem.category
                        && oldItem.muscles == newItem.muscles
            }
        }
    }
}