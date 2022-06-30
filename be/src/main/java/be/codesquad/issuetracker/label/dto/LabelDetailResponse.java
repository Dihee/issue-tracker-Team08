package be.codesquad.issuetracker.label.dto;

import be.codesquad.issuetracker.label.domain.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LabelDetailResponse {

    private Long id;
    private String title;
    private String description;
    private String color;

    public LabelDetailResponse(Label label) {
        this.id = label.getId();
        this.title = label.getTitle();
        this.description = label.getDescription();
        this.color = label.getColor();
    }
}
