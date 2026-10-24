package com.portfolio.issue_tracker_api.project.service;

import com.portfolio.issue_tracker_api.project.dto.ProjectRequest;
import com.portfolio.issue_tracker_api.project.dto.ProjectResponse;
import com.portfolio.issue_tracker_api.project.entity.Project;
import com.portfolio.issue_tracker_api.project.repository.ProjectRepository;
import com.portfolio.issue_tracker_api.project_membership.entity.ProjectMembership;
import com.portfolio.issue_tracker_api.project_membership.repository.ProjectMembershipRepository;
import com.portfolio.issue_tracker_api.user.entity.User;
import com.portfolio.issue_tracker_api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMembershipRepository projectMembershipRepository;
    private final ProjectAuthorizationService projectAuthorizationService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, ProjectMembershipRepository projectMembershipRepository, ProjectAuthorizationService projectAuthorizationService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMembershipRepository = projectMembershipRepository;
        this.projectAuthorizationService = projectAuthorizationService;
    }

    public Project createProject(ProjectRequest request) {
        Project newProject = new Project(
                request.getName(),
                request.getKey(),
                request.getDescription()
        );
        return projectRepository.save(newProject);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void addUserToProject(Long projectId, String currentUsername, Long userId) {
        Project project = projectAuthorizationService.validateUserAccess(projectId, currentUsername);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        ProjectMembership projectMembership = new ProjectMembership(project, user);
        projectMembershipRepository.save(projectMembership);
    }

    public List<User> getUsersOfProject(Long projectId, String currentUsername){
        projectAuthorizationService.validateUserAccess(projectId, currentUsername);

        return projectMembershipRepository.findByProjectId(projectId).stream()
                .map(ProjectMembership::getUser)
                .collect(Collectors.toList());
    }

    public void removeUserFromProject(Long projectId, String currentUsername, Long userId) {
        projectAuthorizationService.validateUserAccess(projectId, currentUsername);
        ProjectMembership projectMembership = projectMembershipRepository.findByProject_IdAndUser_Id(projectId, userId)
                .orElseThrow(() -> new RuntimeException("ProjectMembership not found"));
        projectMembershipRepository.delete(projectMembership);
    }
}
