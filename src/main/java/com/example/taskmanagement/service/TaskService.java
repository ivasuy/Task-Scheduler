package com.example.taskmanagement.service;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.model.TaskRequest;

import java.util.List;

public interface TaskService {


    List<Task> getTaskByUserId(Long userId);

    String createTask(TaskRequest taskRequest, Long userId);

    String updateTask(Long taskId, TaskRequest taskRequest, Long userId);

    String deleteTask(Long taskId, Long userId);
}
