package org.linkdev.notificationservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateRequestDto {
    private String name;
    private String description;
    private String senderEmailAddress;
    private String senderEmailSubject;
    private String senderEmailBody;
}
