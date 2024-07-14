package org.linkdev.notificationservice.controller;

import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    ResponseEntity<Object> createTemplate(@RequestBody TemplateRequestDto requestDto) {
        try {
            templateService.createTemplate(requestDto);
        } catch (TemplateException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
        return null;
    }

    @GetMapping("/{templateId}")
    ResponseEntity<Object> getTemplateById(@PathVariable("templateId") Integer templateId) {
        try {
            return ResponseEntity.ok(templateService.getTemplateById(templateId));
        } catch (TemplateException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PutMapping("/{templateId}")
    void updateTemplateById(@PathVariable("templateId") String templateId,
                            @RequestBody TemplateRequestDto requestDto) {

    }


    @DeleteMapping
    void deleteTemplateById(@RequestBody List<Integer> templateIdsList) {
        templateService.deleteTemplateById(templateIdsList);
    }


}
