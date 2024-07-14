package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        if (Objects.isNull(requestDto.getName())) {
            throw new TemplateException(HttpStatus.BAD_REQUEST, TemplateErrorMessages.INVALID_TEMPLATE_REQUEST);
        }
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(requestDto);
        repository.save(templateRecord);
    }

    public TemplateResponseDto getTemplateById(Integer templateId) {
        Optional<TemplateRecord> optional = repository.findById(templateId);
        if (optional.isPresent()) {
            return templateMapper.recordToResponseDto(optional.get());
        } else {
            throw new TemplateException(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);
        }
    }

    public void deleteTemplateById(List<Integer> templateIdsList) {
        List<Integer> notFoundTemplateIds = new ArrayList<>();

        for (Integer templateId : templateIdsList) {
            Optional<TemplateRecord> optional = repository.findById(templateId);
            if (optional.isPresent()) {
                repository.deleteById(templateId);
            } else {
                throw new TemplateException(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);
            }
        }
    }
}
