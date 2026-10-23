package com.portfolio.issue_tracker_api.issue.dto;

import com.portfolio.issue_tracker_api.issue.entity.Issue;
import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import com.portfolio.issue_tracker_api.issue.enums.IssueType;
import com.portfolio.issue_tracker_api.user.dto.UserResponse;

public class IssueResponse {
    private Long id;
    private String key;
    private String summary;
    private String description;
    private IssueType type;
    private IssueStatus status;
    private Long projectId;
    private UserResponse reporter;
    private UserResponse assignee;

    public IssueResponse() {}

    public IssueResponse(Long id, String key, String summary, String description, IssueType type, IssueStatus status,
                         Long projectId, UserResponse reporter, UserResponse assignee) {
        this.id = id;
        this.key = key;
        this.summary = summary;
        this.description = description;
        this.type = type;
        this.status = status;
        this.projectId = projectId;
        this.reporter = reporter;
        this.assignee = assignee;
    }

    public static IssueResponse fromEntity(Issue issue) {
        return new IssueResponse(
                issue.getId(),
                issue.getKey(),
                issue.getSummary(),
                issue.getDescription(),
                issue.getType(),
                issue.getStatus(),
                issue.getProject().getId(),
                UserResponse.fromEntity(issue.getReporter()),
                issue.getAssignee() != null ? UserResponse.fromEntity(issue.getAssignee()) : null
        );
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public IssueType getType() {
        return type;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public UserResponse getReporter() {
        return reporter;
    }

    public UserResponse getAssignee() {
        return assignee;
    }
}
