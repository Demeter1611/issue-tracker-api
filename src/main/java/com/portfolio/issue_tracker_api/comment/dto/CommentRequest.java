package com.portfolio.issue_tracker_api.comment.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CommentRequest {
    @NotBlank(message= "Comment content cannot be blank")
    @Length(min = 3, max = 1000, message= "Comment content must be between 3 and 1000 characters")
    private String content;

    public CommentRequest() {
    }

    public CommentRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
