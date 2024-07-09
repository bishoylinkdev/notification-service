package org.linkdev.template.mapper;
import org.linkdev.template.model.Record;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TemplateMapper {
    TemplateMapper instance = Mappers.getMapper(TemplateMapper.class);
    TemplateResponseDTO recordToResponseDTO(Record template);
    Record responseDTOToRecord(TemplateResponseDTO templateresponseDTODTO);

}
