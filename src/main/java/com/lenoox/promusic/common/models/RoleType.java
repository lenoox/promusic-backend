package com.lenoox.promusic.common.models;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    EMPLOYEE(Code.ROLE_EMPLOYEE),
    USER(Code.ROLE_USER);

    private final String authority;

    RoleType(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
    public static class Code {
        public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
        public static final String ROLE_USER = "ROLE_USER";
    }
}