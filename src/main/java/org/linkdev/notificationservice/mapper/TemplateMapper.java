package org.linkdev.notificationservice.mapper;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.linkdev.notificationservice.model.TemplateResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateRecord requestDtoToRecord(TemplateRequestDto requestDto);

    TemplateResponseDto recordToResponseDto(TemplateRecord templateRecord);

    List<TemplateResponseDto> recordListToResponseDtoList(List<TemplateRecord> templateRecord);
}
