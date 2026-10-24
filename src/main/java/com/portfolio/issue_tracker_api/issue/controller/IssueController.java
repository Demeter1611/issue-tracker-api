package com.portfolio.issue_tracker_api.issue.controller;

import com.portfolio.issue_tracker_api.issue.dto.*;
import com.portfolio.issue_tracker_api.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public Page<IssueResponse> getIssues(@PathVariable Long projectId,
                                         @ModelAttribute IssueSearchDto issueSearchDto,
                                         Principal principal) {
        String currentUsername = principal.getName();
        return issueService.getFilteredIssues(projectId, currentUsername, issueSearchDto)
                .map(IssueResponse::fromEntity);
    }

    @PatchMapping("/{issueId}/status")
    public IssueResponse updateIssueStatus(@PathVariable Long projectId, @PathVariable Long issueId, @RequestBody IssueStatusUpdateRequest issueStatusUpdateRequest, Principal principal) {
        String currentUsername = principal.getName();
        return IssueResponse.fromEntity(issueService.updateIssueStatus(projectId, currentUsername, issueId, issueStatusUpdateRequest.getStatus()));
    }

    @PatchMapping("/{issueId}/assignee")
    public IssueResponse updateIssueAssignee(@PathVariable Long projectId, @PathVariable Long issueId, @RequestBody IssueAssigneeUpdateRequest issueAssigneeUpdateRequest, Principal principal) {
        String currentUsername = principal.getName();
        return IssueResponse.fromEntity(issueService.updateIssueAssignee(projectId, currentUsername, issueId, issueAssigneeUpdateRequest.getAssigneeId()));
    }
}
