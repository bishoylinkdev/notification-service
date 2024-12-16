package org.linkdev.template.TemplateException;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Setter
@Getter
public class TemplateExceptions extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public TemplateExceptions(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
