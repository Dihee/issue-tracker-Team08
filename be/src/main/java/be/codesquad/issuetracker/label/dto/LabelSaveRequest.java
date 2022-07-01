package be.codesquad.issuetracker.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LabelSaveRequest {

    private String title;
    private String description;
    private String color;
}
