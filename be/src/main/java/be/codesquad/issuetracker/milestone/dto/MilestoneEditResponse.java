package be.codesquad.issuetracker.milestone.dto;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.domain.Milestone;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MilestoneEditResponse {

    private Long id;
    private Status status;
    private String title;
    private String description;
    private LocalDate dueDate;

    public MilestoneEditResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.status = milestone.getStatus();
        this.title = milestone.getTitle();
        this.description = milestone.getDescription();
        this.dueDate = milestone.getDueDate();
    }
}
