package be.codesquad.issuetracker.issue.dto;

import be.codesquad.issuetracker.issue.domain.Issue;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueListResponse {

    private List<Issue> issues;
}
