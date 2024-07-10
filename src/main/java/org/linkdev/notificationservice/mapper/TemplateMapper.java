package org.linkdev.notificationservice.mapper;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateRecord requestDtoToRecord(TemplateRequestDto requestDto);
}
