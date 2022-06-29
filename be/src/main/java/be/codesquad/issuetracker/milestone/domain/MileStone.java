package be.codesquad.issuetracker.milestone.domain;

import be.codesquad.issuetracker.issue.domain.Issue;
import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.dto.MileStoneSaveRequest;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "milestone")
public class MileStone {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "mileStone")
    private List<Issue> issue;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String title;
    private String description;
    private LocalDate dueDate;

    public MileStone(MileStoneSaveRequest mileStoneSaveRequest) {
        this.title = mileStoneSaveRequest.getTitle();
        this.description = mileStoneSaveRequest.getDescription();
        this.dueDate = mileStoneSaveRequest.getDueDate();
        this.status = Status.OPEN;
    }
}
