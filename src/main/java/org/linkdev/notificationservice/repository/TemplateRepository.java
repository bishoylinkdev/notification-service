package org.linkdev.notificationservice.repository;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends CrudRepository<TemplateRecord, Integer> {
}
