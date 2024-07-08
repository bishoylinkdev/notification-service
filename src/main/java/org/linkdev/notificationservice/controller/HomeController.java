package org.linkdev.notificationservice.controller;

import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template")
public class HomeController {

    @PostMapping
    void createTemplate(@RequestBody TemplateRequestDto requestDto) {
        String name = requestDto.getName();
        String description = requestDto.getDescription();
        String senderEmailAddress = requestDto.getSenderEmailAddress();
        String insertQuery = String.format("INSERT INTO template (id, name, description)\n" +
                "VALUES (%s, '%s', '%s')", 1, name, requestDto.getDescription());
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
