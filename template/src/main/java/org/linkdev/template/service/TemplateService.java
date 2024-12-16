package org.linkdev.template.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.linkdev.template.TemplateException.TemplateErrorMessages;
import org.linkdev.template.TemplateException.TemplateExceptions;
import org.linkdev.template.mapper.TemplateMapper;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.linkdev.template.repository.TemplateRepository;
import org.linkdev.template.model.Record;
import org.mapstruct.ap.shaded.freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.linkdev.template.TemplateValidation.validateTemplateRequest;


@Service
@Slf4j
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    @Autowired
    public TemplateService(TemplateRepository templateRepository, TemplateMapper templateMapperpper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapperpper;
    }


    public Optional<TemplateRequestDTO> getTemplateById(String templateId) {
        if (templateRepository.existsById(templateId)) {
            Optional<Record> record = templateRepository.findById(templateId);
            return record.map(templateMapper::recordToRequestDTO);
        }else{
            throw new TemplateExceptions(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);
        }

    }


    public List<TemplateResponseDTO> getTemplatesList(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Record> recordpage = templateRepository.findAll(pageable);
        List<TemplateResponseDTO> responseDTOs = templateMapper.recordListToResponseDtoList(recordpage.getContent());
        return responseDTOs;
    }
    @Async
    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public void createTemplate(TemplateRequestDTO requestDto) throws RuntimeException {
            log.info("service before thread is : {}", Thread.currentThread().getName());
            validateTemplateRequest(requestDto);
            Record record = templateMapper.requestDTOToRecord(requestDto);
           // Thread.sleep(5000); // Simulate delay
            log.info("service after thread is : {}", Thread.currentThread().getName());
            templateRepository.save(record);

    }
    @Async
    @Recover
    public void recoverTemplate(RuntimeException e,TemplateRequestDTO requestDto) {
        log.info( "Check Template Data");
        log.info(e.getMessage());
    }


    public TemplateRequestDTO updateTemplate( String templateid, TemplateRequestDTO requestdto) {
        Optional<Record> record = templateRepository.findById(templateid);
        if(record.isPresent()) {
            Record templateRecord = record.get();
            templateMapper.updateTemplateFromDto(requestdto,templateRecord);
            Record updatedRecord =templateRepository.save(templateRecord);
            return templateMapper.recordToRequestDTO(updatedRecord);
        }
        else {return null;}



    }


    public void deleteTemplateById(String templateId) {
        if(templateRepository.existsById(templateId)) {
            templateRepository.deleteById(templateId);
        } else{
            throw new TemplateExceptions(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);
        }

    }
    @Transactional
    public void deleteTemplatelistById(List<Integer> templateIdList) {
        for(Integer templateId : templateIdList) {
            Optional<Record> optionalRecord = templateRepository.findById(String.valueOf(templateId));
            if(optionalRecord.isPresent()) {
                templateRepository.deleteById(String.valueOf(templateId));
            }else{
                throw new TemplateExceptions(HttpStatus.NOT_FOUND, TemplateErrorMessages.TEMPLATE_NOT_FOUND);

            }
        }
    }

}