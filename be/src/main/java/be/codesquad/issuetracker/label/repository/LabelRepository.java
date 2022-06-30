package be.codesquad.issuetracker.label.repository;

import be.codesquad.issuetracker.label.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

}
