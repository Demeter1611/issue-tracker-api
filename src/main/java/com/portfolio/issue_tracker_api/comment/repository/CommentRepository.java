package com.portfolio.issue_tracker_api.comment.repository;

import com.portfolio.issue_tracker_api.comment.entity.Comment;
import com.portfolio.issue_tracker_api.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIssue(Issue issue);
}
