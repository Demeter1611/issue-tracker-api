package com.portfolio.issue_tracker_api.project.dto;

import com.portfolio.issue_tracker_api.project.entity.Project;

public class ProjectResponse {
    private Long id;
    private String name;
    private String key;
    private String description;

    public ProjectResponse() {}

    public ProjectResponse(Long id, String name, String key, String description) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.description = description;
    }

    public static ProjectResponse fromEntity(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getKey(),
                project.getDescription()
        );
    }

    public Long getId() {
        return id;
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
}
