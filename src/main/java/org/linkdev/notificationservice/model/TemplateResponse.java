package org.linkdev.notificationservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateResponse {
    private String id;
    private String name;
    private String description;
    private String senderEmailAddress;
    private String senderEmailSubject;
    private String senderEmailBody;
}
