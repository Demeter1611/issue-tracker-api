package com.portfolio.issue_tracker_api.project.service;

import com.portfolio.issue_tracker_api.project.entity.Project;
import com.portfolio.issue_tracker_api.project.repository.ProjectRepository;
import com.portfolio.issue_tracker_api.project_membership.repository.ProjectMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


@Service
public class ProjectAuthorizationService {
    private final ProjectRepository projectRepository;
    private final ProjectMembershipRepository projectMembershipRepository;

    @Autowired
    public ProjectAuthorizationService(ProjectRepository projectRepository, ProjectMembershipRepository projectMembershipRepository) {
        this.projectRepository = projectRepository;
        this.projectMembershipRepository = projectMembershipRepository;
    }

    public Project validateUserAccess(Long projectId, String username) {
        Project project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        boolean isMember = this.projectMembershipRepository.existsByProject_IdAndUser_Username(projectId, username);
        if(!isMember) {
            throw new AccessDeniedException("User is not a member of the project");
        }

        return project;
    }
}
