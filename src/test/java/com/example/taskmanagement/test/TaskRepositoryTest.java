package com.example.taskmanagement.test;

import com.example.taskmanagement.entity.Status;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(
        connection = EmbeddedDatabaseConnection.H2
)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTask(){

        Task task = Task.builder()
                .taskDescription("Do Coding")
                .taskStatus(Status.PENDING)
                .taskCreationTime(Instant.now())
                .taskUpdationTime(Instant.now())
                .taskPriority(1)
                .build();

        Task saveTask = taskRepository.save(task);

        assertThat(saveTask).isNotNull();
        assertThat(saveTask.getTaskId()).isGreaterThan(0);

    }

    @Test
    public void getTaskByUser(){

        User user = User.builder()
                .userName("Vasu Yadav")
                .userEmail("vasu7yadav@gmail.com")
                .userPassword("123@")
                .build();

        User saveUser = userRepository.save(user);

        Task task = Task.builder()
                .taskDescription("Do Coding")
                .taskStatus(Status.PENDING)
                .taskCreationTime(Instant.now())
                .taskUpdationTime(Instant.now())
                .taskPriority(1)
                .assignedUser(user)
                .build();

        List<Task> tasks = taskRepository.findByAssignedUserUserId(task.getAssignedUser().getUserId());

        if (!tasks.isEmpty()) {
            Task findTask = tasks.get(0);
            assertThat(findTask).isNotNull();
        }
    }

    @Test
    public void updateTask(){

        Task task = Task.builder()
                .taskDescription("Do Coding")
                .taskStatus(Status.PENDING)
                .taskCreationTime(Instant.now())
                .taskUpdationTime(Instant.now())
                .taskPriority(1)
                .build();

        taskRepository.save(task);

        Task getTask = taskRepository.findById(task.getTaskId()).get();
        getTask.setTaskDescription("Do this Project");
        getTask.setTaskStatus(Status.DONE);

        Task updateTask = taskRepository.save(getTask);

        assertThat(updateTask.getTaskDescription()).isNotNull();
        assertThat(updateTask.getTaskStatus()).isNotNull();
    }

    @Test
    public void deleteTask(){

        Task task = Task.builder()
                .taskDescription("Do Coding")
                .taskStatus(Status.PENDING)
                .taskCreationTime(Instant.now())
                .taskUpdationTime(Instant.now())
                .taskPriority(1)
                .build();
        taskRepository.save(task);

        taskRepository.deleteById(task.getTaskId());
        Optional<Task> tasks = taskRepository.findById(task.getTaskId());

        assertThat(tasks).isEmpty();
    }

}
