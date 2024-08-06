package org.linkdev.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduledJobService {

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        log.info("scheduled thread : {}", Thread.currentThread().getName());
    }
}
