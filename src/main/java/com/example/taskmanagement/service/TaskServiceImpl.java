package com.example.taskmanagement.service;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.exception.CustomException;
import com.example.taskmanagement.model.TaskRequest;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String createTask(TaskRequest taskRequest, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User with given userId does not exist","USER_NOT_FOUND"));

        Task task = Task.builder()
                .taskCategory(taskRequest.getTaskCategory())
                .taskStatus(taskRequest.getTaskStatus())
                .taskDescription(taskRequest.getTaskDescription())
                .taskCreationTime(Instant.now())
                .taskUpdationTime(Instant.now())
                .taskPriority(taskRequest.getTaskPriority())
                .assignedUser(user)
                .build();

        taskRepository.save(task);

        return "Task Created !";
    }

    @Override
    @Cacheable(
            cacheNames = "tasks",
            key = "#userId"
    )
    public List<Task> getTaskByUserId(Long userId) {
        List<Task> tasks = taskRepository.findByAssignedUserUserId(userId);

        if(tasks.isEmpty()) throw new CustomException("No Task with given UserId exist","TASK_NOT_FOUND");

        return tasks;
    }

    @Override
    @CachePut(
            cacheNames = "tasks",
            key = "#userId"
    )
    public String updateTask(Long taskId, TaskRequest taskRequest, Long userId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new CustomException("Task with given taskId does not exist","TASK_NOT_FOUND"));

        if (task.getAssignedUser().getUserId() != userId) throw new CustomException("User does not have access to Update this task","UNAUTHORIZED_ACCESS");

        if(taskRequest.getTaskStatus() != null) task.setTaskStatus(taskRequest.getTaskStatus());
        if(taskRequest.getTaskCategory() != null) task.setTaskCategory(taskRequest.getTaskCategory());
        if(taskRequest.getTaskPriority() != null) task.setTaskPriority(taskRequest.getTaskPriority());
        if(taskRequest.getTaskDescription() != null) task.setTaskDescription(taskRequest.getTaskDescription());

        task.setTaskUpdationTime(Instant.now());

        return "Task Updated !";
    }

    @Override
    @CacheEvict(
            cacheNames = "tasks",
            key = "#taskId"
    )
    public String deleteTask(Long taskId, Long userId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new CustomException("Task with given taskId does not exist","TASK_NOT_FOUND"));

        if (task.getAssignedUser().getUserId() != userId) throw new CustomException("User does not have access to delete this task","UNAUTHORIZED_ACCESS");

        taskRepository.delete(task);

        return "Task Deleted !";
    }

}
