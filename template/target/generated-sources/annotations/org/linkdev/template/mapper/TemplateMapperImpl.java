package org.linkdev.template.mapper;

import javax.annotation.processing.Generated;
import org.linkdev.template.model.Record;
import org.linkdev.template.model.TemplateResponseDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-09T19:23:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class TemplateMapperImpl implements TemplateMapper {

    @Override
    public TemplateResponseDTO recordToResponseDTO(Record template) {
        if ( template == null ) {
            return null;
        }

        TemplateResponseDTO templateResponseDTO = new TemplateResponseDTO();

        return templateResponseDTO;
    }

    @Override
    public Record responseDTOToRecord(TemplateResponseDTO templateresponseDTODTO) {
        if ( templateresponseDTODTO == null ) {
            return null;
        }

        Record record = new Record();

        return record;
    }
}
