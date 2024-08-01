package org.linkdev.notificationservice.repository;

import org.linkdev.notificationservice.model.TemplateStateStoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateStateStoreRepository extends JpaRepository<TemplateStateStoreRecord, Integer> {
}
