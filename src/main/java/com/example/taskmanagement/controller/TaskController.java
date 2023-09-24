package com.example.taskmanagement.controller;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.model.TaskRequest;
import com.example.taskmanagement.model.TaskResponse;
import com.example.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest, @RequestParam Long userId){

        String createResponse = taskService.createTask(taskRequest, userId);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskResponse>> getTaskByUserId(@PathVariable ("userId") Long userId){

        List<Task> tasks = taskService.getTaskByUserId(userId);

        List<TaskResponse> taskResponses = tasks.stream()
                .map(task -> {
                    TaskResponse taskResponse = new TaskResponse();
                    taskResponse.setTaskDescription(task.getTaskDescription());
                    taskResponse.setTaskStatus(task.getTaskStatus());
                    taskResponse.setTaskCategory(task.getTaskCategory());
                    taskResponse.setTaskPriority(task.getTaskPriority());
                    taskResponse.setTaskCreationTime(task.getTaskCreationTime());
                    taskResponse.setTaskUpdationTime(task.getTaskUpdationTime());
                    return taskResponse;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(taskResponses,HttpStatus.FOUND);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable ("taskId") Long taskId, @RequestBody TaskRequest taskRequest, @RequestParam Long userId){

        String updateStatus = taskService.updateTask(taskId, taskRequest, userId);
        return new ResponseEntity<>(updateStatus, HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable ("taskId") Long taskId, @RequestParam Long userId){

        String deleteStatus = taskService.deleteTask(taskId, userId);
        return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
    }

}
