package org.linkdev.template.service;

import org.linkdev.template.mapper.TemplateMapper;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.linkdev.template.repository.TemplateRepository;
import org.linkdev.template.model.Record;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper mapper= TemplateMapper.instance;


    TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<Record> getAllTemplates() {
        return templateRepository.findAll();
    }
   /* public void createTemplate(TemplateRequestDTO requestDto) {
       Record record = mapper.responseDTOToRecord(requestDto);
        templateRepository.save(record);
    }*/

    public Optional<TemplateResponseDTO> getTemplateById(String templateId) {
        Optional<Record> record = templateRepository.findById(templateId);
        return record.map(mapper::recordToResponseDTO);

    }

    public TemplateResponseDTO createOrUpdateTemplate(TemplateResponseDTO templateDTO) {
        Record template = mapper.responseDTOToRecord(templateDTO);
        template = templateRepository.save(template);
        return mapper.recordToResponseDTO(template);
    }
    public void deleteTemplateById(String templateId) {
        templateRepository.deleteById(templateId);
        System.out.println("deleted successfully");
    }
}