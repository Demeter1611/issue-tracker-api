package com.portfolio.issue_tracker_api.issue.dto;

import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import jakarta.validation.constraints.NotNull;

public class IssueStatusUpdateRequest {
    @NotNull(message = "Status cannot be null")
    private IssueStatus status;

    public IssueStatusUpdateRequest() {}

    public IssueStatusUpdateRequest(IssueStatus status) {
        this.status = status;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }
}
