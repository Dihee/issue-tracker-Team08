package be.codesquad.issuetracker.label.controller;

import be.codesquad.issuetracker.label.dto.LabelResponse;
import be.codesquad.issuetracker.label.dto.LabelSaveRequest;
import be.codesquad.issuetracker.label.service.LabelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public List<LabelResponse> getLabels() {
        return labelService.findAll();
    }

    @GetMapping("/{id}")
    public LabelResponse getLabel(@PathVariable Long id) {
        return labelService.findById(id);
    }

    @PostMapping
    public Long save(LabelSaveRequest dto) {
        return labelService.save(dto);
    }

}
