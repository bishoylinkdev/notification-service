package org.linkdev.template.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.linkdev.template.model.Record;
import org.linkdev.template.model.TemplateRequestDTO;
import org.linkdev.template.model.TemplateResponseDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T09:38:01+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@Component
public class TemplateMapperImpl implements TemplateMapper {

    @Override
    public TemplateRequestDTO recordToRequestDTO(Record template) {
        if ( template == null ) {
            return null;
        }

        TemplateRequestDTO templateRequestDTO = new TemplateRequestDTO();

        templateRequestDTO.setTempname( template.getTempname() );
        templateRequestDTO.setDiscription( template.getDiscription() );
        templateRequestDTO.setEmailAddress( template.getEmailAddress() );
        templateRequestDTO.setEmailSubject( template.getEmailSubject() );
        templateRequestDTO.setPhone( template.getPhone() );
        templateRequestDTO.setEmailBody( template.getEmailBody() );

        return templateRequestDTO;
    }

    @Override
    public Record requestDTOToRecord(TemplateRequestDTO requestdto) {
        if ( requestdto == null ) {
            return null;
        }

        Record record = new Record();

        record.setTempname( requestdto.getTempname() );
        record.setDiscription( requestdto.getDiscription() );
        record.setEmailAddress( requestdto.getEmailAddress() );
        record.setEmailSubject( requestdto.getEmailSubject() );
        record.setPhone( requestdto.getPhone() );
        record.setEmailBody( requestdto.getEmailBody() );

        return record;
    }

    @Override
    public List<TemplateResponseDTO> recordListToResponseDtoList(List<Record> templateRecord) {
        if ( templateRecord == null ) {
            return null;
        }

        List<TemplateResponseDTO> list = new ArrayList<TemplateResponseDTO>( templateRecord.size() );
        for ( Record record : templateRecord ) {
            list.add( recordToTemplateResponseDTO( record ) );
        }

        return list;
    }

    @Override
    public void updateTemplateFromDto(TemplateRequestDTO templateRequestDTO, Record templateRecord) {
        if ( templateRequestDTO == null ) {
            return;
        }

        templateRecord.setTempname( templateRequestDTO.getTempname() );
        templateRecord.setDiscription( templateRequestDTO.getDiscription() );
        templateRecord.setEmailAddress( templateRequestDTO.getEmailAddress() );
        templateRecord.setEmailSubject( templateRequestDTO.getEmailSubject() );
        templateRecord.setPhone( templateRequestDTO.getPhone() );
        templateRecord.setEmailBody( templateRequestDTO.getEmailBody() );
    }

    protected TemplateResponseDTO recordToTemplateResponseDTO(Record record) {
        if ( record == null ) {
            return null;
        }

        TemplateResponseDTO templateResponseDTO = new TemplateResponseDTO();

        templateResponseDTO.setTempid( record.getTempid() );
        templateResponseDTO.setTempname( record.getTempname() );
        templateResponseDTO.setDiscription( record.getDiscription() );
        templateResponseDTO.setEmailAddress( record.getEmailAddress() );
        templateResponseDTO.setEmailSubject( record.getEmailSubject() );
        templateResponseDTO.setPhone( record.getPhone() );
        templateResponseDTO.setEmailBody( record.getEmailBody() );

        return templateResponseDTO;
    }
}
