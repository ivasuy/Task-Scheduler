package com.example.taskmanagement.model;

import com.example.taskmanagement.entity.Status;
import com.example.taskmanagement.entity.TaskCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    private String taskDescription;
    private Status taskStatus;
    private Integer taskPriority;
    private TaskCategory taskCategory;
}
