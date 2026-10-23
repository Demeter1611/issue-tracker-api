package com.portfolio.issue_tracker_api.project_membership.entity;

import com.portfolio.issue_tracker_api.project.entity.Project;
import com.portfolio.issue_tracker_api.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "project_memberships")
public class ProjectMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public ProjectMembership() {}

    public ProjectMembership(Project project, User user) {
           this.project = project;
           this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Project getProject() {
        return project;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
