package com.lenoox.promusic.users.mapper;

import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    private final BCryptPasswordEncoder passwordEncoder;
    private final  RoleRepository roleRepository;
    private final  RoleMapper roleMapper;
    public UserMapper(
            BCryptPasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            RoleMapper roleMapper) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public User paramToEntity(UserParam userParam, String emailDomainAuth) {
        User user = new User();
        user.setUsername(userParam.getUsername());
        user.setFirstName(userParam.getFirstName());
        user.setLastName(userParam.getLastName());
        user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        if(userParam.getUsername().endsWith(emailDomainAuth)){
            user.setRole(roleRepository.getByRole(RoleType.EMPLOYEE.toString()));
        } else{
            user.setRole(roleRepository.getByRole(RoleType.USER.toString()));
        }
        user.setAddress(userParam.getAddress());
        user.setPhoneNumber(userParam.getPhoneNumber());
        user.setCity(userParam.getCity());
        user.setActive(true);
        return user;
    }

    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setCity(user.getCity());
        userDto.setActive(user.getActive());
        return userDto;
    }
}
