package org.linkdev.notificationservice.mapper;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemplateMapper {
    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);
    TemplateRecord map(TemplateRequestDto requestDto);
    //TemplateRequestDto recordToRequest(TemplateRecord record);


}
