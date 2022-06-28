package be.codesquad.issuetracker.issue.controller;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.issue.dto.IssueListResponse;
import be.codesquad.issuetracker.issue.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public IssueListResponse getIssues(String status) {
        return issueService.findAll(Status.of(status));
    }
}
