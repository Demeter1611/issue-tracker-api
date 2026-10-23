package com.portfolio.issue_tracker_api.project.entity;

import com.portfolio.issue_tracker_api.project_membership.entity.ProjectMembership;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    private String key;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectMembership> memberships;

    @Column(nullable = false)
    private Long issueCounter = 0L;

    public Project() {}

    public Project(String name, String key, String description) {
        this.name = name;
        this.key = key;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public Long getIssueCounter() {
        return issueCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void incrementIssueCounter() {
        this.issueCounter++;
    }
}
