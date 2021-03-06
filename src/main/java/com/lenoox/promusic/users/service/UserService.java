package com.lenoox.promusic.users.service;

import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.Param.UserPasswordParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;

import java.util.List;

public interface UserService {
    UserDto save(UserParam user);
    List<UserDto> getAll();
    UserWithRolesDTO getByUsername(String id);
    UserDto update(String id, UserParam user);
    Boolean updatePassword(UserPasswordParam userPasswordParam, String email);
    void delete(Long id);
}
