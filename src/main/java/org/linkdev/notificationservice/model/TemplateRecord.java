package org.linkdev.notificationservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity(name = "template")
public class TemplateRecord {
    @Id
    private String id;
    private String name;
    private String description;
    private String senderEmailAddress;
    private String senderEmailSubject;
    private String senderEmailBody;
    private String senderMobileNumber;
}
