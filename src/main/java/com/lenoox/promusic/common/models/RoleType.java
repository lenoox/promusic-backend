package com.lenoox.promusic.common.models;

public enum RoleType {
    EMPLOYEE(Role.ROLE_EMPLOYEE), USER(Role.ROLE_USER);

    RoleType(String name) {
    }

    public static class Role {
        public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
        public static final String ROLE_USER = "ROLE_USER";
    }
}