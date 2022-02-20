package com.lenoox.promusic.common.utils;

import lombok.extern.java.Log;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Log
@Service
public class ShowActiveProfile {
    private final Environment env;

    public ShowActiveProfile(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void showProfile() {
        log.info("Active profile: " + Arrays.asList(env.getActiveProfiles()).toString());
    }
}
