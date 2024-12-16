package org.linkdev.template.mapper;
import org.linkdev.template.model.Record;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateRequestDTO recordToRequestDTO(Record template);
    Record requestDTOToRecord(TemplateRequestDTO requestdto);
    List<TemplateResponseDTO> recordListToResponseDtoList(List<Record> templateRecord);

    void updateTemplateFromDto(TemplateRequestDTO templateRequestDTO, @MappingTarget Record templateRecord);


}
