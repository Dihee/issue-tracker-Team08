package be.codesquad.issuetracker.milestone.dto;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.domain.MileStone;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MileStoneDetailResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final LocalDate dueDate;
    private final Status status;

    public MileStoneDetailResponse(MileStone mileStone) {
        this.id = mileStone.getId();
        this.title = mileStone.getTitle();
        this.description = mileStone.getDescription();
        this.dueDate = mileStone.getDueDate();
        this.status = mileStone.getStatus();
    }
}
