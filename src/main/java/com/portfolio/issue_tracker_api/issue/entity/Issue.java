package com.portfolio.issue_tracker_api.issue.entity;

import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import com.portfolio.issue_tracker_api.issue.enums.IssueType;
import com.portfolio.issue_tracker_api.project.entity.Project;
import com.portfolio.issue_tracker_api.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name="issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueType type;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    public Issue() {}

    public Issue(String key, String summary, String description, IssueType type, IssueStatus status, Project project, User reporter, User assignee) {
        this.key = key;
        this.summary = summary;
        this.description = description;
        this.type = type;
        this.status = status;
        this.project = project;
        this.reporter = reporter;
        this.assignee = assignee;
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
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

    public Project getProject() {
        return project;
    }

    public User getReporter() {
        return reporter;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setKey(String issueKey) {
        this.key = issueKey;
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

    public void setProject(Project project) {
        this.project = project;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

}
