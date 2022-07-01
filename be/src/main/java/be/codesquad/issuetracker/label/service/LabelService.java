package be.codesquad.issuetracker.label.service;

import be.codesquad.issuetracker.label.domain.Label;
import be.codesquad.issuetracker.label.dto.LabelResponse;
import be.codesquad.issuetracker.label.dto.LabelSaveRequest;
import be.codesquad.issuetracker.label.repository.LabelRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    @Transactional(readOnly = true)
    public LabelResponse findById(Long id) {
        Label findLabel = findByIdOrThrow(id);
        return new LabelResponse(findLabel);
    }

    @Transactional
    public Long save(LabelSaveRequest labelSaveRequest) {
        Label label = new Label(labelSaveRequest);
        Label saveLabel = labelRepository.save(label);
        return saveLabel.getId();
    }

    @Transactional(readOnly = true)
    public List<LabelResponse> findAll() {
        return labelRepository.findAll().stream()
            .map(LabelResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Label findLabel = findByIdOrThrow(id);
        labelRepository.delete(findLabel);
    }

    private Label findByIdOrThrow(Long id) {
        return labelRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    }

}
