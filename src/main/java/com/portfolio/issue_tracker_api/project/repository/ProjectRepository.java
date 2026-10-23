package com.portfolio.issue_tracker_api.project.repository;

import com.portfolio.issue_tracker_api.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
