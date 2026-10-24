package com.portfolio.issue_tracker_api.comment.dto;

import com.portfolio.issue_tracker_api.comment.entity.Comment;

public class CommentResponse {
    private Long id;
    private String content;
    private String issueKey;
    private String authorUsername;

    public CommentResponse() {}

    public CommentResponse(Long id, String content, String issueKey, String authorUsername) {
        this.id = id;
        this.content = content;
        this.issueKey = issueKey;
        this.authorUsername = authorUsername;
    }

    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getIssue().getKey(),
                comment.getAuthor().getUsername()
        );
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }
}
