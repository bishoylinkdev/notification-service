package org.linkdev.notificationservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TemplateException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public TemplateException(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
