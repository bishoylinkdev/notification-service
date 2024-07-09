package org.linkdev.template.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int tempid;
    public String tempname;
    public String discription;
    public String EmailAddress;
    public String EmailBody;
    public String EmailSubject;

}
