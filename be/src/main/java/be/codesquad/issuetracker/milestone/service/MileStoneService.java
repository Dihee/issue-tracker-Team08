package be.codesquad.issuetracker.milestone.service;

import be.codesquad.issuetracker.issue.domain.Status;
import be.codesquad.issuetracker.milestone.domain.MileStone;
import be.codesquad.issuetracker.milestone.dto.MileStoneDetailResponse;
import be.codesquad.issuetracker.milestone.dto.MileStoneListResponse;
import be.codesquad.issuetracker.milestone.dto.MileStoneSaveRequest;
import be.codesquad.issuetracker.milestone.repository.MileStoneRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MileStoneService {

    private final MileStoneRepository mileStoneRepository;

    @Transactional(readOnly = true)
    public MileStoneListResponse findAll(Status status) {
        List<MileStone> milestones = mileStoneRepository.findAllByStatus(status);
        return new MileStoneListResponse(milestones);
    }

    @Transactional(readOnly = true)
    public MileStoneDetailResponse findById(Long id) {
        MileStone findMileStone = findByIdOrThrow(id);
        return new MileStoneDetailResponse(findMileStone);
    }

    @Transactional
    public void save(MileStoneSaveRequest mileStoneSaveRequest) {
        MileStone mileStone = new MileStone(mileStoneSaveRequest);
        mileStoneRepository.save(mileStone);
    }

    public void delete(Long id) {
        MileStone findMilestone = findByIdOrThrow(id);
        mileStoneRepository.delete(findMilestone);
    }

    private MileStone findByIdOrThrow(Long id) {
        return mileStoneRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    }
}
