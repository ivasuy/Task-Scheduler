package com.example.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String taskDescription;
    @Enumerated(EnumType.STRING)
    private Status taskStatus;
    private Integer taskPriority;
    @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;
    private Instant taskCreationTime;
    private Instant taskUpdationTime;
    @ManyToOne
    @JoinColumn(
            name = "userId"
    )
    private User assignedUser;
}
