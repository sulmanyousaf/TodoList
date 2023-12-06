package com.sulman.todolist.ui.fragment.second

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel
import com.sulman.todolist.databinding.ItemTaskBinding

class SecondFragmentAdapter(
    private val taskInfo: List<TodoModel>,
    val onItemClicked: (TodoModel) -> Unit,
    val onDeleteClicked: (TodoModel) -> Unit
) :
    RecyclerView.Adapter<SecondFragmentAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        Log.e("ApiResponse", "binding item:${taskInfo[position]}")
        holder.bind(taskInfo[position])
    }

    override fun getItemCount(): Int {
        return taskInfo.size
    }

    inner class VideoViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todoModel: TodoModel) {
            binding.apply {
                title.text = todoModel.title

                cardView.setOnClickListener {
                    onItemClicked.invoke(todoModel)
                }

                ivDelete.setOnClickListener {
                    onDeleteClicked.invoke(todoModel)
                }
            }
        }
    }
}


