package com.lenoox.promusic.users.dtos;

import com.lenoox.promusic.users.models.Role;

public class UserWithRolesDTO extends UserDto{
    private Role role;

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
