package be.codesquad.issuetracker.milestone.service;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.domain.Milestone;
import be.codesquad.issuetracker.milestone.dto.MileStoneDetailResponse;
import be.codesquad.issuetracker.milestone.dto.MileStoneListResponse;
import be.codesquad.issuetracker.milestone.dto.MilestoneRequest;
import be.codesquad.issuetracker.milestone.repository.MilestoneRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MileStoneService {

    private final MilestoneRepository milestoneRepository;

    @Transactional(readOnly = true)
    public MileStoneListResponse findAll(Status status) {
        List<Milestone> milestones = milestoneRepository.findAllByStatus(status);
        return new MileStoneListResponse(milestones);
    }

    @Transactional(readOnly = true)
    public MileStoneDetailResponse findById(Long id) {
        Milestone findMilestone = findByIdOrThrow(id);
        return new MileStoneDetailResponse(findMilestone);
    }

    @Transactional
    public void save(MilestoneRequest mileStoneSaveRequest) {
        Milestone milestone = new Milestone(mileStoneSaveRequest);
        milestoneRepository.save(milestone);
    }

    @Transactional
    public void delete(Long id) {
        Milestone findMilestone = findByIdOrThrow(id);
        milestoneRepository.delete(findMilestone);
    }

    @Transactional
    public Milestone edit(Long id, MilestoneRequest milestoneRequest) {
        Milestone findMilestone = findByIdOrThrow(id);
        findMilestone.editMilestone(
            milestoneRequest.getTitle(),
            milestoneRequest.getDescription(),
            milestoneRequest.getDueDate(),
            milestoneRequest.getStatus());
        return findMilestone;
    }

    private Milestone findByIdOrThrow(Long id) {
        return milestoneRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    }
}
