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
    private String tempname;
    private String discription;
    private String emailAddress;
    private String emailSubject;
    private String phone;
    private String emailBody;

}
