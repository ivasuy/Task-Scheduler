package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.UserRequest;
import com.example.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserDetails(
            @PathVariable ("userId") Long userId,
            @RequestBody UserRequest userRequest
    ){

        String updateStatus = userService.updateUserDetails(userId, userRequest);
        return new ResponseEntity<>(updateStatus, HttpStatus.CREATED);
    }

}
