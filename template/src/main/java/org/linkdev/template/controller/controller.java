package org.linkdev.template.controller;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.linkdev.template.service.TemplateService;
import org.linkdev.template.model.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/template")
public class controller {

    private final TemplateService templateService;

    controller(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<TemplateResponseDTO> getTemplateById(@PathVariable String templateId) {
        Optional<TemplateResponseDTO> templateDTO = templateService.getTemplateById(templateId);
        return templateDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<TemplateResponseDTO> createOrUpdateTemplate(@RequestBody TemplateResponseDTO templateDTO) {
        TemplateResponseDTO updatedTemplate = templateService.createOrUpdateTemplate(templateDTO);
        return ResponseEntity.ok(updatedTemplate);
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<String> deleteTemplateById(@PathVariable String templateId) {
        try {
            templateService.deleteTemplateById(templateId);
            return ResponseEntity.ok("Entity with ID " + templateId + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete entity");
        }
    }
}