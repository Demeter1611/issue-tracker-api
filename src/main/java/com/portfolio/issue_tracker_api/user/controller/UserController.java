package com.portfolio.issue_tracker_api.user.controller;

import com.portfolio.issue_tracker_api.user.dto.UserRequest;
import com.portfolio.issue_tracker_api.user.dto.UserResponse;
import com.portfolio.issue_tracker_api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
