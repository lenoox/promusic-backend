package com.lenoox.promusic.common.services;

import lombok.extern.java.Log;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    public Optional<Long> getCurrentAuditor() {
        Long userid = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            userid = ((UserDetailsImpl) principal).getUser().getId();
        }
        return Optional.ofNullable(userid);
    }
}