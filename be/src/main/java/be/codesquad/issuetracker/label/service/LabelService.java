package be.codesquad.issuetracker.label.service;

import be.codesquad.issuetracker.label.domain.Label;
import be.codesquad.issuetracker.label.dto.LabelDetailResponse;
import be.codesquad.issuetracker.label.dto.LabelSaveRequest;
import be.codesquad.issuetracker.label.repository.LabelRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LabelService {

    private final LabelRepository labelRepository;

    @Transactional(readOnly = true)
    public LabelDetailResponse findById(Long id) {
        log.info("id: {}", id);
        log.debug("labelSerivce: {}", labelRepository.findAll().size());
        Label findLabel = labelRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
        return new LabelDetailResponse(findLabel);
    }

    @Transactional
    public Long save(LabelSaveRequest labelSaveRequest) {
        Label label = new Label(labelSaveRequest);
        Label saveLabel = labelRepository.save(label);
        return saveLabel.getId();
    }

    @Transactional(readOnly = true)
    public List<Label> findAll() {
        return labelRepository.findAll();
    }
}
