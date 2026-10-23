package com.portfolio.issue_tracker_api.issue.controller;

import com.portfolio.issue_tracker_api.issue.dto.IssueAssigneeUpdateRequest;
import com.portfolio.issue_tracker_api.issue.dto.IssueCreateRequest;
import com.portfolio.issue_tracker_api.issue.dto.IssueResponse;
import com.portfolio.issue_tracker_api.issue.dto.IssueStatusUpdateRequest;
import com.portfolio.issue_tracker_api.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects/{projectId}/issues")
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public IssueResponse createIssue(@PathVariable Long projectId, @RequestBody IssueCreateRequest issueCreateRequest, Principal principal) {
        String currentUserName = principal.getName();
        return IssueResponse.fromEntity(issueService.createIssue(projectId, currentUserName, issueCreateRequest));
    }

    @GetMapping
    public List<IssueResponse> getIssues(@PathVariable Long projectId) {
        return issueService.getIssuesByProject(projectId).stream()
                .map(IssueResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{issueId}/status")
    public IssueResponse updateIssueStatus(@PathVariable Long projectId, @PathVariable Long issueId, @RequestBody IssueStatusUpdateRequest issueStatusUpdateRequest) {
        return IssueResponse.fromEntity(issueService.updateIssueStatus(projectId, issueId, issueStatusUpdateRequest.getStatus()));
    }

    @PatchMapping("/{issueId}/assignee")
    public IssueResponse updateIssueAssignee(@PathVariable Long projectId, @PathVariable Long issueId, @RequestBody IssueAssigneeUpdateRequest issueAssigneeUpdateRequest) {
        return IssueResponse.fromEntity(issueService.updateIssueAssignee(projectId, issueId, issueAssigneeUpdateRequest.getAssigneeId()));
    }
}
