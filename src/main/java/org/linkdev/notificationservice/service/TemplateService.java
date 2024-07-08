package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.linkdev.notificationservice.mapper.TemplateMapper;

@Service
public class TemplateService {

    private final TemplateRepository repository;
    private final TemplateMapper mapper = TemplateMapper.INSTANCE;
    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public void createTemplate(TemplateRequestDto requestDto) {
        /*TemplateRecord record = new TemplateRecord();
        record.setName("template1");
        record.setDescription("test1");
        record.setSenderEmailBody("test1@email.com");
        record.setSenderEmailSubject("email-subject");
        repository.save(record);*/
        TemplateRecord record = mapper.map(requestDto);
        repository.save(record);
    }
}
