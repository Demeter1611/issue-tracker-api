package com.portfolio.issue_tracker_api.comment.entity;

import com.portfolio.issue_tracker_api.issue.entity.Issue;
import com.portfolio.issue_tracker_api.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Comment() {}

    public Comment(String content, Issue issue, User author) {
        this.content = content;
        this.issue = issue;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Issue getIssue() {
        return issue;
    }

    public User getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
