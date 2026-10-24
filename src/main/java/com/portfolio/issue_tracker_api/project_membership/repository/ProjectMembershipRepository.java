package com.portfolio.issue_tracker_api.project_membership.repository;

import com.portfolio.issue_tracker_api.project_membership.entity.ProjectMembership;
import com.portfolio.issue_tracker_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMembershipRepository extends JpaRepository<ProjectMembership, Long> {
    List<ProjectMembership> findByProjectId(Long projectId);
    Optional<ProjectMembership> findByProject_IdAndUser_Id(Long projectId, Long userId);
    Boolean existsByProject_IdAndUser_Username(Long projectId, String username);
}
