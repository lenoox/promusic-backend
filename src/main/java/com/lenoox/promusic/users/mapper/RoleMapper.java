package com.lenoox.promusic.users.mapper;

import com.lenoox.promusic.users.dtos.RoleDto;
import com.lenoox.promusic.users.models.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto entityToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
