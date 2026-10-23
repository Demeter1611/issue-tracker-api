package com.portfolio.issue_tracker_api.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectRequest {
    @NotBlank(message = "Project name required")
    @Size(min = 3, max = 20, message = "Project name must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Project key required")
    @Size(min = 2, max = 10, message = "Project key must be between 2 and 10 characters")
    private String key;

    @Size(max = 255, message = "Project description must be less than 255 characters")
    private String description;

    public ProjectRequest() {}

    public ProjectRequest(String name, String key, String description) {
        this.name = name;
        this.key = key;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
