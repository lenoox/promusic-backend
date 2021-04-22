package com.lenoox.promusic.users.mapper;

import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.RoleDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserWithRolesMapper {

    private final  RoleMapper roleMapper;
    public UserWithRolesMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    public UserWithRolesDTO entityToDto(User user) {
        UserWithRolesDTO userDto = new UserWithRolesDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setCity(user.getCity());
        userDto.setActive(user.getActive());
        RoleDto roleDto = roleMapper.entityToDto(user.getRole());
        userDto.setRole(roleDto);
        return userDto;
    }
}
