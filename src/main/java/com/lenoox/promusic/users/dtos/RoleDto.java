package com.lenoox.promusic.users.dtos;

import com.lenoox.promusic.common.models.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private Long id;
    private RoleType name;
}
