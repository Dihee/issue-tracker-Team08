package be.codesquad.issuetracker.issue.service;

import be.codesquad.issuetracker.issue.domain.Issue;
import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.issue.dto.IssueListResponse;
import be.codesquad.issuetracker.issue.repository.IssueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    @Transactional(readOnly = true)
    public IssueListResponse findAll(Status status) {
        List<Issue> issues = issueRepository.findAllByStatus(status);
        return new IssueListResponse(issues);
    }
}
