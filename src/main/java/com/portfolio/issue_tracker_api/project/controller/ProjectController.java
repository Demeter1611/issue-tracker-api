package com.portfolio.issue_tracker_api.project.controller;

import com.portfolio.issue_tracker_api.project.dto.ProjectRequest;
import com.portfolio.issue_tracker_api.project.dto.ProjectResponse;
import com.portfolio.issue_tracker_api.project.service.ProjectService;
import com.portfolio.issue_tracker_api.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> getProjects() {
        return projectService.getAllProjects().stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {
        return ProjectResponse.fromEntity(projectService.createProject(projectRequest));
    }

    @GetMapping("{projectId}/members")
    public List<UserResponse> getUsersOfProject(@PathVariable Long projectId) {
        return projectService.getUsersOfProject(projectId).stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @PostMapping("{projectId}/members/{userId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.addUserToProject(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{projectId}/members/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.removeUserFromProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
