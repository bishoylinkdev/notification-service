package org.linkdev.template.controller;
import org.linkdev.template.TemplateException.TemplateErrorMessages;
import org.linkdev.template.TemplateException.TemplateExceptions;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.linkdev.template.service.TemplateService;
import org.linkdev.template.model.Record;
import org.mapstruct.ap.shaded.freemarker.template.TemplateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/template")
public class controller {

    private final TemplateService templateService;

    controller(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<TemplateRequestDTO> getTemplateById(@PathVariable String templateId) {
        Optional<TemplateRequestDTO> templateDTO = templateService.getTemplateById(templateId);
        return templateDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping
    public ResponseEntity<List<TemplateResponseDTO>> getTemplatesList(@RequestParam int page, @RequestParam int size) {
        List<TemplateResponseDTO> templates = templateService.getTemplatesList(page, size);
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createTemplate(@RequestBody TemplateRequestDTO requestDto)  {
        try {
            templateService.createTemplate(requestDto);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(TemplateErrorMessages.INVALID_TEMPLATE_REQUEST);

        }
        return ResponseEntity.ok(requestDto);
    }

    @PutMapping("/{templateId}")
    public ResponseEntity<TemplateRequestDTO> updateTemplate(@PathVariable String templateId, @RequestBody TemplateRequestDTO templateDTO) {
        TemplateRequestDTO updatedTemplate=templateService.updateTemplate(templateId, templateDTO);
        if(updatedTemplate!=null) {
            return ResponseEntity.ok(updatedTemplate);
        }
        else {return ResponseEntity.notFound().build();}
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<String> deleteTemplateById(@PathVariable String templateId) {
        try {
            templateService.deleteTemplateById(templateId);
            return ResponseEntity.ok("Entity with ID " + templateId + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete entity");
        }
    }

        @DeleteMapping
        public ResponseEntity<String> deleteTemplatelistById(@RequestBody List<Integer> templateId){
            if(templateId==null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("template with ID" + templateId + " was not found");
            }
            templateService.deleteTemplatelistById(templateId);
            return ResponseEntity.ok("Entity with ID " + templateId + " deleted successfully");


        }
    }
