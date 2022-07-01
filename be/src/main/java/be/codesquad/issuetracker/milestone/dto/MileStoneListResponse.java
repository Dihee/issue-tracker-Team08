package be.codesquad.issuetracker.milestone.dto;

import be.codesquad.issuetracker.milestone.domain.Milestone;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MileStoneListResponse {

    private List<Milestone> mileStones;
}
