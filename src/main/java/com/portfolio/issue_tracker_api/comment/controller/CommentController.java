package com.portfolio.issue_tracker_api.comment.controller;

import com.portfolio.issue_tracker_api.comment.dto.CommentRequest;
import com.portfolio.issue_tracker_api.comment.dto.CommentResponse;
import com.portfolio.issue_tracker_api.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/issues/{issueId}/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getComments(@PathVariable Long projectId, @PathVariable Long issueId, Principal principal) {
        String currentUsername = principal.getName();
        return commentService.getCommentsByIssue(projectId, issueId, currentUsername);
    }

    @PostMapping
    public CommentResponse createComment(@PathVariable Long projectId, @PathVariable Long issueId, @RequestBody CommentRequest request, Principal principal) {
        String currentUsername = principal.getName();
        return commentService.createComment(projectId, issueId, currentUsername, request);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long projectId, @PathVariable Long issueId, @PathVariable Long commentId, Principal principal) {
        String currentUsername = principal.getName();
        commentService.deleteComment(projectId, issueId, commentId, currentUsername);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long projectId, @PathVariable Long issueId, @PathVariable Long commentId, Principal principal, @RequestBody CommentRequest request) {
        String currentUsername = principal.getName();
        return commentService.updateComment(projectId, issueId, commentId, currentUsername, request);
    }
}
