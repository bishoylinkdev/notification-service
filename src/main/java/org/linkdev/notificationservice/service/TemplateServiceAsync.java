package org.linkdev.notificationservice.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateStateStoreRecord;
import org.linkdev.notificationservice.model.TemplateStatus;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.linkdev.notificationservice.repository.TemplateStateStoreRepository;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TemplateServiceAsync {

    private final TemplateRepository repository;
    private final TemplateMapper templateMapper;

    private final TemplateStateStoreRepository templateStateStoreRepository;

    public TemplateServiceAsync(TemplateRepository repository, TemplateMapper templateMapper,
                                TemplateStateStoreRepository templateStateStoreRepository) {
        this.repository = repository;
        this.templateMapper = templateMapper;
        this.templateStateStoreRepository = templateStateStoreRepository;
    }

    @SneakyThrows
    @Async
    public void saveTemplate(TemplateRecord templateRecord) {
        log.info("saveTemplate thread is : {}", Thread.currentThread().getName());
        Thread.sleep(15000);
        repository.save(templateRecord);
        TemplateStateStoreRecord stateStoreRecord = new TemplateStateStoreRecord();
        stateStoreRecord.setId(templateRecord.getId());
        stateStoreRecord.setTemplateStatus(TemplateStatus.CREATED.name());
        templateStateStoreRepository.save(stateStoreRecord);
    }
}
