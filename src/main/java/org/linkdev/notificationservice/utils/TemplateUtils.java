package org.linkdev.notificationservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.linkdev.notificationservice.exception.TemplateErrorMessages;
import org.linkdev.notificationservice.exception.TemplateException;
import org.linkdev.notificationservice.model.TemplateRequestDto;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateUtils {

    public static void validateTemplateRequest(TemplateRequestDto templateRequestDto) {
        if (templateRequestDto.getName().isBlank() ||
                templateRequestDto.getSenderEmailAddress().isBlank()) {
            throw new TemplateException(HttpStatus.BAD_REQUEST, TemplateErrorMessages.INVALID_TEMPLATE_REQUEST);
        }
    }
}
