package org.linkdev.notificationservice.controller;

import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.model.TemplatePage;
import org.linkdev.notificationservice.model.TemplateRequest;
import org.linkdev.notificationservice.service.TemplateService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    ResponseEntity<Object> createTemplate(@RequestBody TemplateRequest requestDto) {
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

    @GetMapping
    ResponseEntity<TemplatePage> getTemplatesList(@Param("pageSize") Integer pageSize,
                                                  @Param("pageNumber") Integer pageNumber,
                                                  @Param("orderBy") String orderBy) {
        return ResponseEntity.ok(templateService.getTemplatesList(pageSize, pageNumber, orderBy));
    }

    @PutMapping("/{templateId}")
    void updateTemplateById(@PathVariable("templateId") String templateId,
                            @RequestBody TemplateRequest requestDto) {

    }


    @DeleteMapping
    void deleteTemplateById(@RequestBody List<Integer> templateIdsList) {
        templateService.deleteTemplateById(templateIdsList);
    }


}
