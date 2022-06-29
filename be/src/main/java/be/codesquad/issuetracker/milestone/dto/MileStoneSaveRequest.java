package be.codesquad.issuetracker.milestone.dto;

import be.codesquad.issuetracker.issue.domain.Status;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MileStoneSaveRequest {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;
}
