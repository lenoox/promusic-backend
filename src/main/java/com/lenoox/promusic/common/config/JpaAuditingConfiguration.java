package com.lenoox.promusic.common.config;

import com.lenoox.promusic.common.services.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<Long> auditorAware(){
        return new AuditorAwareImpl();
    }
}