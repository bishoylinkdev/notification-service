package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplatePage;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequest;
import org.linkdev.notificationservice.model.TemplateResponse;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.linkdev.notificationservice.utils.TemplateUtils.validateTemplateRequest;


@Service
public class TemplateService {

    private final TemplateRepository repository;

    private final TemplateMapper templateMapper;

    public TemplateService(TemplateRepository repository, TemplateMapper templateMapper) {
        this.repository = repository;
        this.templateMapper = templateMapper;
    }

    public void createTemplate(TemplateRequest templateRequest) {
        validateTemplateRequest(templateRequest);
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(templateRequest);
        repository.save(templateRecord);
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
