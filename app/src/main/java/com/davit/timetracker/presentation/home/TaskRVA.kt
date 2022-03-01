package com.davit.timetracker.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.davit.timetracker.databinding.TrackedItemBinding
import com.davit.timetracker.domain.models.Task

class TaskRVA : ListAdapter<Task, TaskRVA.TaskViewHolder>(DiffutilsCallback()) {
    var shareTask: ((String) -> Unit)? = null
    var showTimer: ((Task)-> Unit)? = null

    class DiffutilsCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.task == newItem.task
        }

    }


    inner class TaskViewHolder(private val binding: TrackedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.descriptionName.text = task.description
            binding.taskName.text = task.task
            binding.timeName.text = task.time
            binding.shareButton.setOnClickListener {
                shareTask?.invoke(task.time)
            }

            binding.startButton.setOnClickListener {
                showTimer?.invoke(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TrackedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}