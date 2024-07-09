package org.linkdev.template.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemplateRequestDTO {

    public String tempname;
    public String discription;
    public String EmailAddress;
    public String EmailBody;
    public String EmailSubject;
}
