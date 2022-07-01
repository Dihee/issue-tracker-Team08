package be.codesquad.issuetracker.milestone.repository;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.domain.Milestone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findAllByStatus(Status status);
}
