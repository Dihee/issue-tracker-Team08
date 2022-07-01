package be.codesquad.issuetracker.label.domain;

import be.codesquad.issuetracker.issue.domain.Issue;
import be.codesquad.issuetracker.label.dto.LabelSaveRequest;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Label {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Issue issue;

    private String title;
    private String description;
    private String color;

    public Label(LabelSaveRequest labelSaveRequest) {
        this.title = labelSaveRequest.getTitle();
        this.description = labelSaveRequest.getDescription();
        this.color = labelSaveRequest.getColor();
    }
}
