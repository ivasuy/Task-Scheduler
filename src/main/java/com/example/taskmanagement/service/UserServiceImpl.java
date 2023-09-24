package com.example.taskmanagement.service;

import com.example.taskmanagement.entity.Role;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.exception.CustomException;
import com.example.taskmanagement.model.UserRequest;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public UserServiceImpl(UserRepository userRepository) {}

    @Override
    public String addNewUser(UserRequest userRequest) {
        User user = User.builder()
                .userEmail(userRequest.getUserEmail())
                .userName(userRequest.getUserName())
                .userPassword(userRequest.getUserPassword())
                .role(Role.ROLE_USER).build();

        userRepository.save(user);

        return "New User Created !";
    }

    @Override
    public String updateUserDetails(Long userId, UserRequest userRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User with given userId does not exist","USER_NOT_FOUND"));

        if(userRequest.getUserName() != null) user.setUserName(userRequest.getUserName());
        if(userRequest.getUserEmail() != null) user.setUserEmail(userRequest.getUserEmail());
        if(userRequest.getUserPassword() != null) user.setUserPassword(userRequest.getUserPassword());

        userRepository.save(user);

        return "User Details Updated !";
    }

    @Override
    public String deleteUser(Long userId) {

        List<Task> tasks = taskRepository.findByAssignedUserUserId(userId);
        taskRepository.deleteAll(tasks);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User with given userId does not exist","USER_NOT_FOUND"));

        userRepository.delete(user);

        return "User Deleted !";
    }


}
