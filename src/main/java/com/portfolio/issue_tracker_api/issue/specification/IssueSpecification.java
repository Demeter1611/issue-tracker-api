package com.portfolio.issue_tracker_api.issue.specification;

import com.portfolio.issue_tracker_api.issue.entity.Issue;
import com.portfolio.issue_tracker_api.issue.enums.IssueStatus;
import com.portfolio.issue_tracker_api.issue.enums.IssueType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class IssueSpecification {
    public static Specification<Issue> filterIssues(
            Long projectId,
            IssueStatus status,
            IssueType type,
            String assigneeUsername
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("project").get("id"), projectId));

            if(status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if(type != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), type));
            }

            if(assigneeUsername != null && !assigneeUsername.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("assignee").get("username"), assigneeUsername));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
