package com.portfolio.issue_tracker_api.issue.service;

import com.portfolio.issue_tracker_api.issue.dto.IssueCreateRequest;
import com.portfolio.issue_tracker_api.issue.dto.IssueSearchDto;
import com.portfolio.issue_tracker_api.issue.entity.Issue;
import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import com.portfolio.issue_tracker_api.issue.repository.IssueRepository;
import com.portfolio.issue_tracker_api.issue.specification.IssueSpecification;
import com.portfolio.issue_tracker_api.project.entity.Project;
import com.portfolio.issue_tracker_api.project.repository.ProjectRepository;
import com.portfolio.issue_tracker_api.project.service.ProjectAuthorizationService;
import com.portfolio.issue_tracker_api.user.entity.User;
import com.portfolio.issue_tracker_api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectAuthorizationService projectAuthorizationService;

    @Autowired
    public IssueService(IssueRepository issueRepository, ProjectRepository projectRepository, UserRepository userRepository, ProjectAuthorizationService projectAuthorizationService) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectAuthorizationService = projectAuthorizationService;
    }

    public Page<Issue> getFilteredIssues(Long projectId, String currentUsername, IssueSearchDto searchDto){
        Project project = projectAuthorizationService.validateUserAccess(projectId, currentUsername);

        Specification<Issue> spec = IssueSpecification.filterIssues(
                projectId,
                searchDto.getStatus(),
                searchDto.getType(),
                searchDto.getAssigneeUsername()
        );

        Pageable pageable = searchDto.toPageable();

        return issueRepository.findAll(spec, pageable);
    }

    public Issue createIssue(Long projectId, String currentUserName, IssueCreateRequest request) {
        Project project = projectAuthorizationService.validateUserAccess(projectId, currentUserName);

        User reporter = userRepository.findByUsername(currentUserName)
                .orElseThrow(() -> new IllegalArgumentException("Reporter not found"));

        User assignee = null;
        if(request.getAssigneeId() != null) {
            assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new IllegalArgumentException("Assignee not found"));
        }

        project.incrementIssueCounter();
        projectRepository.save(project);

        String issueKey = project.getKey() + "-" + project.getIssueCounter();

        Issue newIssue = new Issue(
                issueKey,
                request.getSummary(),
                request.getDescription(),
                request.getType(),
                request.getStatus() != null ? request.getStatus() : IssueStatus.TO_DO,
                project,
                reporter,
                assignee
        );

        return issueRepository.save(newIssue);
    }

    public Issue updateIssueStatus(Long projectId, String currentUsername, Long issueId, IssueStatus newStatus) {
        projectAuthorizationService.validateUserAccess(projectId, currentUsername);

        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found"));

        if(!issue.getProject().getId().equals(projectId)) {
            throw new IllegalArgumentException("Issue does not belong to the specified project");
        }

        issue.setStatus(newStatus);
        return issueRepository.save(issue);
    }

    public Issue updateIssueAssignee(Long projectId, String currentUsername, Long issueId, Long assigneeId) {
        projectAuthorizationService.validateUserAccess(projectId, currentUsername);

        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found"));

        if(!issue.getProject().getId().equals(projectId)) {
            throw new IllegalArgumentException("Issue does not belong to the specified project");
        }

        User assignee = null;
        if (assigneeId != null){
            assignee = userRepository.findById(assigneeId)
                    .orElseThrow(() -> new IllegalArgumentException("Assignee not found"));
        }

        issue.setAssignee(assignee);
        return issueRepository.save(issue);
    }
}
