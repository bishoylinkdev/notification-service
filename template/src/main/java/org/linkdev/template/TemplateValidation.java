package org.linkdev.template;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.linkdev.template.TemplateException.TemplateErrorMessages;
import org.linkdev.template.TemplateException.TemplateExceptions;
import org.linkdev.template.model.TemplateRequestDTO;
import org.mapstruct.ap.shaded.freemarker.template.TemplateException;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class TemplateValidation {
    public static void validateTemplateRequest(TemplateRequestDTO templateRequestdto)  {
        if (templateRequestdto.getTempname().isBlank() ||
                templateRequestdto.getEmailAddress().isBlank()){
            throw new TemplateExceptions(HttpStatus.BAD_REQUEST,TemplateErrorMessages.INVALID_TEMPLATE_REQUEST);
        }
    }

    }
