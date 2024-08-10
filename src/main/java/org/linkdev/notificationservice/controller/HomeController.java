package org.linkdev.notificationservice.controller;

import org.hibernate.query.Page;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.linkdev.notificationservice.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.data.domain.Page;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<TemplateResponseDto>> getTemplates(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<TemplateResponseDto> responseDtoList = templateService.getTemplates(page, size);

        return ResponseEntity.ok(responseDtoList);
    }


    @PutMapping("/{templateId}")
    TemplateRecord updateTemplateById(@PathVariable("templateId") Integer templateId,
                            @RequestBody TemplateRequestDto requestDto) {

        TemplateRecord response=templateService.updateTemplate(templateId,requestDto );

        return response;

    }


    @DeleteMapping()
    void deleteTemplateById(@RequestBody List<Integer> templateIds) {

        templateService.deleteTemplateById(templateIds);
    }


}
