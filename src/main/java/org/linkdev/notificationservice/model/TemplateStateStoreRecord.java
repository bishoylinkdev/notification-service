package org.linkdev.notificationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "template_state_store")
public class TemplateStateStoreRecord {
    @Id
    private String id;

    private String templateStatus;
}
