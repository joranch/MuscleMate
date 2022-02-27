package com.monarc.musclemate.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monarc.musclemate.data.entities.WorkoutPlan
import com.monarc.musclemate.databinding.ItemWorkoutListBinding

class WorkoutListAdapter(private val onItemClicked: (WorkoutPlan) -> Unit) :
    ListAdapter<WorkoutPlan, WorkoutListAdapter.WorkoutPlanViewHolder>(DiffCallback) {

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
        fun bind(item: WorkoutPlan) {
            binding.workout = item
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WorkoutPlan>() {
            override fun areItemsTheSame(oldItem: WorkoutPlan, newItem: WorkoutPlan): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WorkoutPlan, newItem: WorkoutPlan): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.lastWorkoutDate == newItem.lastWorkoutDate
                        && oldItem.title == newItem.title
                        && oldItem.description == newItem.description
            }
        }
    }
}