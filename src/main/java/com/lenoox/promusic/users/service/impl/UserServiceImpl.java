package com.lenoox.promusic.users.service.impl;

import com.lenoox.promusic.common.exception.DuplicateException;
import com.lenoox.promusic.common.exception.UserNotFoundException;
import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;
import com.lenoox.promusic.users.mapper.UserMapper;
import com.lenoox.promusic.users.mapper.UserWithRolesMapper;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.UserRepository;
import com.lenoox.promusic.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String emailDomainAuth = "@gmail.com";

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserWithRolesMapper userWithRolesMapper;

    public List<UserDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.entityToDto(user))
                .collect(Collectors.toList());
    }

    public UserWithRolesDTO getByUsername(String email) {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        UserWithRolesDTO userWithRolesDTO = userWithRolesMapper.entityToDto(user);

        return userWithRolesDTO;
    }

    @Override
    public UserDto save(UserParam userParam) {
        User userWithDuplicateEmail = userRepository.findByUsername(userParam.getUsername())
                .orElseThrow(() -> new UserNotFoundException(userParam.getUsername()));;
        if(userWithDuplicateEmail != null && userParam.getUsername() != userWithDuplicateEmail.getUsername()) {
            log.error(String.format("Duplicate email %s", userParam.getUsername()));
            throw new DuplicateException(userParam.getUsername());
        }
        User user = userMapper.paramToEntity(userParam,emailDomainAuth);
        User userSaved = userRepository.save(user);
        UserDto userDto =  userMapper.entityToDto(userSaved);
        return userDto;
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
