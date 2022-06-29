package be.codesquad.issuetracker.milestone.repository;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.domain.MileStone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {

    List<MileStone> findAllByStatus(Status status);
}
