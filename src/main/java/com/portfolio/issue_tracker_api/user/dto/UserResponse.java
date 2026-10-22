package com.portfolio.issue_tracker_api.user.dto;

import com.portfolio.issue_tracker_api.user.entity.User;

public class UserResponse {
    private Long id;
    private String username;

    public UserResponse() {}

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserResponse fromEntity(User user) {
        if (user == null){
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUsername()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}


