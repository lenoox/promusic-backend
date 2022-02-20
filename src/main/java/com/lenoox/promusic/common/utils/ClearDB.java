package com.lenoox.promusic.common.utils;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log
@Component
public class ClearDB {
    @Autowired
    Flyway flyway;

    @Profile("!prod")
    @Transactional
    @Scheduled(cron = "${job.cron:-}")
    public void clean() {
        log.info("Clear database in dev profile");
        flyway.clean();
        flyway.migrate();
    }
}
