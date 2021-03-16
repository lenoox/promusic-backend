package com.lenoox.promusic.users.service;

import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.models.User;

import java.util.List;

public interface UserService {
    UserDto save(UserDto user);
    List<UserDto> findAll();
    User findEmail(String id);
    void delete(long id);
}
