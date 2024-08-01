package org.linkdev.notificationservice.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.*;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.linkdev.notificationservice.repository.TemplateStateStoreRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.linkdev.notificationservice.utils.TemplateUtils.validateTemplateRequest;


@Service
@Slf4j
public class TemplateService {

    private final TemplateRepository repository;

    private final TemplateStateStoreRepository templateStateStoreRepository;
    private final TemplateMapper templateMapper;

    private final TemplateServiceAsync templateServiceAsync;


    public TemplateService(TemplateRepository repository, TemplateStateStoreRepository templateStateStoreRepository,
                           TemplateMapper templateMapper,
                           TemplateServiceAsync templateServiceAsync) {
        this.repository = repository;
        this.templateStateStoreRepository = templateStateStoreRepository;
        this.templateMapper = templateMapper;
        this.templateServiceAsync = templateServiceAsync;
    }

    public void createTemplate(TemplateRequest templateRequest) {
        log.info("service thread is : {}", Thread.currentThread().getName());
        validateTemplateRequest(templateRequest);
        String templateId = UUID.randomUUID().toString();
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(templateRequest);
        templateRecord.setId(templateId);
        TemplateStateStoreRecord stateStoreRecord = new TemplateStateStoreRecord();
        stateStoreRecord.setId(templateId);
        stateStoreRecord.setTemplateStatus(TemplateStatus.IN_PROGRESS.name());
        templateStateStoreRepository.save(stateStoreRecord);
        templateServiceAsync.saveTemplate(templateRecord);
    }


    @Transactional
    public TemplateResponse getTemplateById(Integer templateId) {
        Optional<TemplateRecord> optional = repository.findById(templateId);
        if (optional.isPresent()) {
            return templateMapper.recordToResponseDto(optional.get());
        } else {
            throw new TemplateException(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);
        }
    }

    @Transactional
    public void deleteTemplateById(List<Integer> templateIdsList) {
        for (Integer templateId : templateIdsList) {
            Optional<TemplateRecord> optional = repository.findById(templateId);
            if (optional.isPresent()) {
                repository.deleteById(templateId);
            } else {
                throw new TemplateException(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);
            }
        }
    }

    public TemplatePage getTemplatesList(Integer pageSize, Integer pageNumber, String orderBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(orderBy).ascending());
        Page<TemplateRecord> templateRecordPage = repository.findAll(pageRequest);
        TemplatePage pageResponseDto = new TemplatePage();
        pageResponseDto.setPageSize(pageSize);
        pageResponseDto.setPageNumber(pageNumber);
        pageResponseDto.setTotalElements(templateRecordPage.getTotalElements());
        List<TemplateRecord> templateRecordList = templateRecordPage.getContent();
        List<TemplateResponse> templateResponseList = templateMapper
                .recordListToResponseDtoList(templateRecordList);
        pageResponseDto.setContent(templateResponseList);
        return pageResponseDto;
    }
}
