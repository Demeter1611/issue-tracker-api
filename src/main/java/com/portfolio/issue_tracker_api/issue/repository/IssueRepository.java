package com.portfolio.issue_tracker_api.issue.repository;

import com.portfolio.issue_tracker_api.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProject_Id(Long projectId);
}
