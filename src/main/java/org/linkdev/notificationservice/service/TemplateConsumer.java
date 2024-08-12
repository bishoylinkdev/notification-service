package org.linkdev.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequest;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TemplateConsumer {

    private final TemplateRepository templateRepository;

    private final TemplateMapper templateMapper;

    public TemplateConsumer(TemplateRepository templateRepository, TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.notification-topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    @SneakyThrows
    public void templateListener(String message) {
        log.info("Received Message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        TemplateRecord templateRecord = objectMapper.readValue(message, TemplateRecord.class);
        templateRepository.save(templateRecord);
    }
}
