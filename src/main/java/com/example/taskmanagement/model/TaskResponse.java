package com.example.taskmanagement.model;

import com.example.taskmanagement.entity.Status;
import com.example.taskmanagement.entity.TaskCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private String taskDescription;
    private Status taskStatus;
    private Integer taskPriority;
    private TaskCategory taskCategory;
    private Instant taskCreationTime;
    private Instant taskUpdationTime;
}
