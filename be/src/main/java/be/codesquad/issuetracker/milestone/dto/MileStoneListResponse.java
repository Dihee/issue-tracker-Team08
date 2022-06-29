package be.codesquad.issuetracker.milestone.dto;

import be.codesquad.issuetracker.milestone.domain.MileStone;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MileStoneListResponse {

    private List<MileStone> mileStones;
}
