package org.linkdev.notificationservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplatePage {
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalElements;
    private List<TemplateResponse> content;
}
