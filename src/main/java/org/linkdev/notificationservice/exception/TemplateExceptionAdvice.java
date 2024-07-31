package org.linkdev.notificationservice.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TemplateExceptionAdvice {

    @ExceptionHandler({TemplateException.class})
    public ResponseEntity<ProblemDto> handleException(TemplateException exception) {
        ProblemDto problemDto = new ProblemDto(exception.getHttpStatus().value(), exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(problemDto);
    }
}
