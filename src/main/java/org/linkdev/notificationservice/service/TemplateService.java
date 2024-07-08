package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.stereotype.Service;


@Service
public class TemplateService {

    private final TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public void createTemplate(TemplateRequestDto requestDto) {
        TemplateRecord record = new TemplateRecord();
        record.setName(requestDto.getName());
        record.setDescription(requestDto.getDescription());
        record.setSenderEmailBody(requestDto.getSenderEmailBody());
        record.setSenderEmailSubject(requestDto.getSenderEmailSubject());
        repository.save(record);
    }
}
