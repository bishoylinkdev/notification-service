package org.linkdev.notificationservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.model.TemplateRequest;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateUtils {

    public static void validateTemplateRequest(TemplateRequest templateRequest) {
        if (Objects.isNull(templateRequest.getName()) ||
                Objects.isNull(templateRequest.getSenderEmailAddress())) {
            throw new TemplateException(HttpStatus.BAD_REQUEST, TemplateErrorMessages.INVALID_TEMPLATE_REQUEST);
        }
        if (templateRequest.getName().isBlank() ||
                templateRequest.getSenderEmailAddress().isBlank()) {
            throw new TemplateException(HttpStatus.BAD_REQUEST, TemplateErrorMessages.INVALID_TEMPLATE_REQUEST);
        }
    }
}
