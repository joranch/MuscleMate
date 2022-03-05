package com.monarc.musclemate.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monarc.musclemate.data.entities.WorkoutRoutine
import com.monarc.musclemate.databinding.ItemWorkoutListBinding

class WorkoutListAdapter(private val onItemClicked: (WorkoutRoutine) -> Unit) :
    ListAdapter<WorkoutRoutine, WorkoutListAdapter.WorkoutPlanViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutPlanViewHolder {
        return WorkoutPlanViewHolder(
            ItemWorkoutListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WorkoutPlanViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener { onItemClicked(current) }
    }


    class WorkoutPlanViewHolder(private var binding: ItemWorkoutListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WorkoutRoutine) {
            binding.workout = item
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WorkoutRoutine>() {
            override fun areItemsTheSame(oldItem: WorkoutRoutine, newItem: WorkoutRoutine): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WorkoutRoutine, newItem: WorkoutRoutine): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.lastWorkoutDate == newItem.lastWorkoutDate
                        && oldItem.title == newItem.title
                        && oldItem.description == newItem.description
            }
        }
    }
}