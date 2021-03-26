package com.lenoox.promusic.users.dtos;

import com.lenoox.promusic.users.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithRolesDTO extends UserDto{
    private RoleDto role;
}
