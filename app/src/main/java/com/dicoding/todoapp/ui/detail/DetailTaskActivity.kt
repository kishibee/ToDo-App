package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.databinding.ActivityTaskDetailBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTaskDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //TODO 11 : Show detail task and implement delete action
        val taskId = intent.getIntExtra(TASK_ID, -1)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: DetailTaskViewModel by viewModels {
            factory
        }
        viewModel.setTaskId(taskId)
        viewModel.task.observe(this, Observer (this::showTask))

        binding.btnDeleteTask.setOnClickListener{
            viewModel.deleteTask()
            finish()
        }
    }

    private fun showTask(task: Task?) {
        if (task != null) {
            binding.detailEdTitle.setText(task.title)
            binding.detailEdDescription.setText(task.description)
            binding.detailEdDueDate.setText(
                DateConverter.convertMillisToString(task.dueDateMillis)
            )
        }
    }
}