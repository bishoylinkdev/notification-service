package org.linkdev.notificationservice.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateErrorMessages {
    public static final String INVALID_TEMPLATE_REQUEST = "Template request not valid";
    public static final String TEMPLATE_NOT_FOUND = "Template with that id not found";
}
