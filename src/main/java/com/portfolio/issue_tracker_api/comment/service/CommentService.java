package com.portfolio.issue_tracker_api.comment.service;

import com.portfolio.issue_tracker_api.comment.dto.CommentRequest;
import com.portfolio.issue_tracker_api.comment.dto.CommentResponse;
import com.portfolio.issue_tracker_api.comment.entity.Comment;
import com.portfolio.issue_tracker_api.comment.repository.CommentRepository;
import com.portfolio.issue_tracker_api.issue.entity.Issue;
import com.portfolio.issue_tracker_api.issue.repository.IssueRepository;
import com.portfolio.issue_tracker_api.project.service.ProjectAuthorizationService;
import com.portfolio.issue_tracker_api.user.entity.User;
import com.portfolio.issue_tracker_api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectAuthorizationService projectAuthorizationService;

    @Autowired
    public CommentService(CommentRepository commentRepository, IssueRepository issueRepository, UserRepository userRepository, ProjectAuthorizationService projectAuthorizationService) {
        this.commentRepository = commentRepository;
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.projectAuthorizationService = projectAuthorizationService;
    }

    private Issue validateIssueAccess(Long projectId, Long issueId, String currentUsername) {
        projectAuthorizationService.validateUserAccess(projectId, currentUsername);
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found"));

        if(!issue.getProject().getId().equals(projectId)){
            throw new IllegalArgumentException("Issue does not belong to the project");
        }

        return issue;
    }

    public CommentResponse createComment(Long projectId, Long issueId, String currentUsername, CommentRequest commentRequest) {
        Issue issue = validateIssueAccess(projectId, issueId, currentUsername);

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return CommentResponse.fromEntity(commentRepository.save(new Comment(commentRequest.getContent(), issue, user)));
    }

    public List<CommentResponse> getCommentsByIssue(Long projectId, Long issueId, String currentUsername) {
        Issue issue = validateIssueAccess(projectId, issueId, currentUsername);

        return commentRepository.findByIssue(issue).stream()
                .map(CommentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CommentResponse updateComment(Long projectId, Long issueId, Long commentId, String currentUsername, CommentRequest commentRequest) {
        validateIssueAccess(projectId, issueId, currentUsername);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("Current user is not the author of the comment");
        }

        comment.setContent(commentRequest.getContent());

        return CommentResponse.fromEntity(commentRepository.save(comment));
    }

    public void deleteComment(Long projectId, Long issueId, Long commentId, String currentUsername) {
        Issue issue = validateIssueAccess(projectId, issueId, currentUsername);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if(!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("Current user is not the author of the comment");
        }

        commentRepository.delete(comment);
    }
}
