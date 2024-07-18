package org.linkdev.notificationservice.controller;

import org.linkdev.notificationservice.api.TemplatesApi;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.mapper.TemplateMapper;
import org.linkdev.notificationservice.model.TemplatePage;
import org.linkdev.notificationservice.model.TemplatePageDTO;
import org.linkdev.notificationservice.model.TemplateRequest;
import org.linkdev.notificationservice.model.TemplateRequestDTO;
import org.linkdev.notificationservice.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateControllerApi implements TemplatesApi {

    private final TemplateService templateService;
    private final TemplateMapper templateMapper;

    public TemplateControllerApi(TemplateService templateService, TemplateMapper templateMapper) {
        this.templateService = templateService;
        this.templateMapper = templateMapper;
    }

    @Override
    public ResponseEntity<Object> createTemplate(TemplateRequestDTO templateRequestDTO) {
        try {
            TemplateRequest templateRequest = templateMapper.requestDTOToTemplateRequest(templateRequestDTO);
            templateService.createTemplate(templateRequest);
        } catch (TemplateException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<TemplatePageDTO> getTemplates(Integer pageNumber, Integer pageSize, String orderBy) {
        TemplatePage templatePage = templateService.getTemplatesList(pageSize, pageNumber, orderBy);
        TemplatePageDTO pageDTO = templateMapper.pageToPageDto(templatePage);
        return ResponseEntity.ok(pageDTO);
    }

}
