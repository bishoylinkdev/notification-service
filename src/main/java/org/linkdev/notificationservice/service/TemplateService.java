package org.linkdev.notificationservice.service;

import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplatePageResponseDto;
import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.linkdev.notificationservice.repository.TemplateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static org.linkdev.notificationservice.utils.TemplateUtils.validateTemplateRequest;


@Service
public class TemplateService {

    private final TemplateRepository repository;

    private final TemplateMapper templateMapper;

    public TemplateService(TemplateRepository repository, TemplateMapper templateMapper) {
        this.repository = repository;
        this.templateMapper = templateMapper;
    }

    public void createTemplate(TemplateRequestDto requestDto) {
        validateTemplateRequest(requestDto);
        TemplateRecord templateRecord = templateMapper.requestDtoToRecord(requestDto);
        repository.save(templateRecord);
    }

    @Transactional
    public TemplateResponseDto getTemplateById(Integer templateId) {
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

    public TemplatePageResponseDto getTemplatesList(Integer pageSize, Integer pageNumber, String orderBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(orderBy).ascending());
        Page<TemplateRecord> templateRecordPage = repository.findAll(pageRequest);
        TemplatePageResponseDto pageResponseDto = new TemplatePageResponseDto();
        pageResponseDto.setPageSize(pageSize);
        pageResponseDto.setPageNumber(pageNumber);
        pageResponseDto.setTotalElements(templateRecordPage.getTotalElements());
        List<TemplateRecord> templateRecordList = templateRecordPage.getContent();
        List<TemplateResponseDto> templateResponseDtoList = templateMapper
                .recordListToResponseDtoList(templateRecordList);
        pageResponseDto.setContent(templateResponseDtoList);
        return pageResponseDto;
    }
}
