package org.linkdev.notificationservice.repository;

import org.linkdev.notificationservice.model.TemplateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateRecord, Integer> {
}
