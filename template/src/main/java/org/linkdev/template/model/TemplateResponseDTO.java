package org.linkdev.template.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemplateResponseDTO {
    private String id;
    private String name;
    private String description;
    private String senderEmailAddress;
    private String senderEmailSubject;
    private String senderEmailBody;
}
