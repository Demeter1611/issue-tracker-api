package com.portfolio.issue_tracker_api.issue.dto;

import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import com.portfolio.issue_tracker_api.issue.enums.IssueType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class IssueSearchDto {
    private int page = 0;
    private int size = 10;
    private String sortBy = "id";
    private String direction = "asc";
    private IssueType type;
    private IssueStatus status;
    private String assigneeUsername;

    public IssueSearchDto() {}

    public IssueSearchDto(int page, int size, String sortBy, String direction, IssueType type, IssueStatus status, String assigneeUsername) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.direction = direction;
        this.type = type;
        this.status = status;
        this.assigneeUsername = assigneeUsername;
    }

    public Pageable toPageable() {
        Sort.Direction dir = "DESC".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page, size, Sort.by(dir, sortBy));
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getDirection() {
        return direction;
    }

    public IssueType getType() {
        return type;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public String getAssigneeUsername() {
        return assigneeUsername;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public void setAssigneeUsername(String assigneeUsername) {
        this.assigneeUsername = assigneeUsername;
    }
}
