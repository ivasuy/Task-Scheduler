package com.example.taskmanagement.service;

import com.example.taskmanagement.model.UserRequest;

public interface UserService {
    String addNewUser(UserRequest userRequest);

    String updateUserDetails(Long userId, UserRequest userRequest);

    String deleteUser(Long userId);
}
