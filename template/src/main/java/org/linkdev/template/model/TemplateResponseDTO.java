package org.linkdev.template.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemplateResponseDTO {
    private int tempid;
    private String tempname;
    private String discription;
    private String EmailAddress;
    private String EmailSubject;
    private String Phone;
    private String EmailBody;

}
