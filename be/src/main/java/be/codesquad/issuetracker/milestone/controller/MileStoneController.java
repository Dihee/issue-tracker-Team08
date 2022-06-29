package be.codesquad.issuetracker.milestone.controller;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.dto.MileStoneListResponse;
import be.codesquad.issuetracker.milestone.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("milestones")
@RequiredArgsConstructor
@Slf4j
public class MileStoneController {

    private final MileStoneService mileStoneService;

    @GetMapping
    public MileStoneListResponse getMileStones(String status) {
        log.debug("status: {}", status);
        return mileStoneService.findAll(Status.of(status));
    }
}
