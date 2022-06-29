package be.codesquad.issuetracker.issue.domain;

import be.codesquad.issuetracker.assignee.domain.Assignee;
import be.codesquad.issuetracker.label.domain.Label;
import be.codesquad.issuetracker.milestone.domain.MileStone;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "issue")
    private List<Label> labels;

    @ManyToOne(fetch = FetchType.LAZY)
    private MileStone mileStone;

    @OneToMany(mappedBy = "issue")
    private List<Assignee> assignees;

    private String subject;
    private String comment;

    public Issue (
        MileStone mileStone,
        List<Label> labels,
        List<Assignee> assignees,
        String subject,
        String comment) {
        this.mileStone = mileStone;
        this.labels = labels;
        this.assignees = assignees;
        this.subject = subject;
        this.comment = comment;
        this.status = Status.OPEN;
    }
}
