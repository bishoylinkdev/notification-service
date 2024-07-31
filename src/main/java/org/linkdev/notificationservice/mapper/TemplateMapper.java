package org.linkdev.notificationservice.mapper;

import org.linkdev.notificationservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateRecord requestDtoToRecord(TemplateRequest requestDto);

    TemplateResponse recordToResponseDto(TemplateRecord templateRecord);

    List<TemplateResponse> recordListToResponseDtoList(List<TemplateRecord> templateRecord);

    TemplateRequest requestDTOToTemplateRequest(TemplateRequestDTO templateRequestDTO);

    TemplateResponseDTO responseToResponseDto(TemplateResponse templateResponse);

    List<TemplateResponseDTO> responseListToResponseDtoList(List<TemplateResponse> templateResponse);

    TemplatePageDTO pageToPageDto(TemplatePage templatePage);
}
