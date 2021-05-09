package com.lenoox.promusic.users.mapper;

import com.lenoox.promusic.users.Param.UserPasswordParam;
import com.lenoox.promusic.users.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordMapper {
    private final BCryptPasswordEncoder passwordEncoder;

    public UserPasswordMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User paramToEntity(User userSaved, UserPasswordParam userParam) {
        userSaved.setPassword(passwordEncoder.encode(userParam.getNewPassword()));
        return userSaved;
    }
}
