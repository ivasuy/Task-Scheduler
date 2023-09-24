package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.UserRequest;
import com.example.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> addNewUser(@RequestBody UserRequest userRequest){

        String createStatus = userService.addNewUser(userRequest);
        return new ResponseEntity<>(createStatus, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable ("userId") Long userId
    ){

        String deleteStatus = userService.deleteUser(userId);
        return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
    }
}
