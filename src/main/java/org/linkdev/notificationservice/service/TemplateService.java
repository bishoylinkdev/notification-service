package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.linkdev.notificationservice.Exceptions.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        if (requestDto == null) {
            throw new BadRequestException("Template request data is invalid.");
        }
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(requestDto);
        repository.save(templateRecord);
    }
    public TemplateResponseDto getTemplateById(Integer templateId){
        Optional<TemplateRecord> optional = repository.findById(templateId);
        if(optional.isPresent()){
        TemplateResponseDto response= templateMapper.RecordToresponseDto(optional.get());
        return response;}
        else{
            throw new TemplateNotFoundException("Template not found with ID: " + templateId);
        }
    }

    public List<TemplateResponseDto> getTemplates(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TemplateRecord> templatePage = repository.findAll(pageable);
        return templatePage.map(templateMapper::RecordToresponseDto).getContent();
    }

    public TemplateRecord updateTemplate(Integer  templateId, TemplateRequestDto requestDto){
        Optional<TemplateRecord> optional = repository.findById(templateId);
        if(optional.isPresent()){
        TemplateRecord existingRecord = optional.get();
        TemplateRecord updatedRecord = templateMapper.requestDtoToRecord(requestDto);
        updatedRecord.setId(existingRecord.getId());
        repository.save(updatedRecord);
        return updatedRecord;}
        else{
            throw new TemplateNotFoundException("Template not found with ID: " + templateId);
        }
    }

    @Transactional
    public void deleteTemplateById(List<Integer> templateIds){

        for(Integer templateId : templateIds) {
            if (repository.existsById(templateId)) {
                repository.deleteById(templateId);
            } else {
                throw new TemplateNotFoundException("Template not found with ID: " + templateId);
            }
        }

    }

}
