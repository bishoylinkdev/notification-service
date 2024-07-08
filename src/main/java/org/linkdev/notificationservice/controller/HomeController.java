package org.linkdev.notificationservice.controller;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.linkdev.notificationservice.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template")
public class HomeController {

    private final TemplateService templateService;

    public HomeController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    void createTemplate(@RequestBody TemplateRequestDto requestDto) {
        templateService.createTemplate(requestDto);
    }

    @GetMapping("/{templateId}")
    TemplateResponseDto getTemplateById(@PathVariable("templateId") String templateId) {
        return null;
    }

    @PutMapping("/{templateId}")
    void updateTemplateById(@PathVariable("templateId") String templateId,
                            @RequestBody TemplateRequestDto requestDto) {

    }


    @DeleteMapping("/{templateId}")
    void deleteTemplateById(@PathVariable("templateId") String templateId) {

    }


}
