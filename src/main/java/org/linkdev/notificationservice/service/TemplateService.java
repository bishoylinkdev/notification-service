package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TemplateService {

    private final TemplateRepository repository;

    private final TemplateMapper templateMapper;

    public TemplateService(TemplateRepository repository, TemplateMapper templateMapper) {
        this.repository = repository;
        this.templateMapper = templateMapper;
    }

    public void createTemplate(TemplateRequestDto requestDto) {
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(requestDto);
        Optional<TemplateRecord> optional = repository.findById(1);
        repository.save(templateRecord);
    }
}
