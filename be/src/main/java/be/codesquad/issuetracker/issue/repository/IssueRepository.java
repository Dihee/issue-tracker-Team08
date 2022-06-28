package be.codesquad.issuetracker.issue.repository;

import be.codesquad.issuetracker.issue.domain.Issue;
import be.codesquad.issuetracker.issue.domain.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAllByStatus(Status status);
}
