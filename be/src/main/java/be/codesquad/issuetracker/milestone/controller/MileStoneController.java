package be.codesquad.issuetracker.milestone.controller;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.dto.MileStoneDetailResponse;
import be.codesquad.issuetracker.milestone.dto.MileStoneListResponse;
import be.codesquad.issuetracker.milestone.dto.MilestoneEditResponse;
import be.codesquad.issuetracker.milestone.dto.MilestoneRequest;
import be.codesquad.issuetracker.milestone.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMileStone(MilestoneRequest milestoneRequest) {
        log.debug("mileStoneSaveRequest: {}", milestoneRequest);
        mileStoneService.save(milestoneRequest);
    }

    @GetMapping("/{id}")
    public MileStoneDetailResponse getMileStoneDetail(@PathVariable Long id) {
        return mileStoneService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMileStone(@PathVariable Long id) {
        mileStoneService.delete(id);
    }

    @PatchMapping("/{id}")
    public MilestoneEditResponse editMilestone(
        @PathVariable Long id,
        @RequestBody MilestoneRequest milestoneRequest) {
        return new MilestoneEditResponse(mileStoneService.edit(id, milestoneRequest));
    }
}
