package org.linkdev.notificationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProblemDto {

    private Integer statusCode;
    private String errorMessage;
}
