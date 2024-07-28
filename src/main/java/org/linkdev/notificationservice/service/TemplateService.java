package org.linkdev.notificationservice.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplatePage;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequest;
import org.linkdev.notificationservice.model.TemplateResponse;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.linkdev.notificationservice.utils.TemplateUtils.validateTemplateRequest;


@Service
@Slf4j
public class TemplateService {

    private final TemplateRepository repository;

    private final TemplateMapper templateMapper;

    private final TaskExecutor taskExecutor;

    public TemplateService(TemplateRepository repository, TemplateMapper templateMapper,
                           TaskExecutor taskExecutor) {
        this.repository = repository;
        this.templateMapper = templateMapper;
        this.taskExecutor = taskExecutor;
    }

    @Async
    public void createTemplate(TemplateRequest templateRequest) {
        log.info("service thread is : {}", Thread.currentThread().getName());
        validateTemplateRequest(templateRequest);
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(templateRequest);
        saveTemplate(templateRecord);
    }

    @SneakyThrows
    private void saveTemplate(TemplateRecord templateRecord) {
        log.info("saveTemplate thread is : {}", Thread.currentThread().getName());
        Thread.sleep(5000);
        repository.save(templateRecord);
        throw new TemplateException(HttpStatus.INTERNAL_SERVER_ERROR, "saveTemplate exception");
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
