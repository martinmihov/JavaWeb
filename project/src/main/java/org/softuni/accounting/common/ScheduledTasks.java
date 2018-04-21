package org.softuni.accounting.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final SessionRegistry sessionRegistry;

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    public ScheduledTasks(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Scheduled(fixedRate = 15000)
    public void checkLoggedInUsersCount() {
        long loggedInUsersCount = this.sessionRegistry.getAllPrincipals().size();

        LOG.info("Currently logged in users count is {}", loggedInUsersCount);
    }
}