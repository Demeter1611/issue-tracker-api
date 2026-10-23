package com.portfolio.issue_tracker_api.issue.dto;

import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import com.portfolio.issue_tracker_api.issue.enums.IssueType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class IssueCreateRequest {
    @NotBlank(message = "Issue summary required")
    @Size(max = 255, message = "Issue summary must be less than 255 characters")
    private String summary;

    @Size(max = 2000, message = "Description is too long")
    private String description;

    @NotNull(message = "Issue type required")
    private IssueType type;

    private IssueStatus status;

    private Long assigneeId;

    public IssueCreateRequest() {}

    public IssueCreateRequest(String summary, String description, IssueType type, IssueStatus status, Long assigneeId) {
        this.summary = summary;
        this.description = description;
        this.type = type;
        this.status = status;
        this.assigneeId = assigneeId;
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

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
}
