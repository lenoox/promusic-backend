package com.lenoox.promusic.users.service.impl;

import com.lenoox.promusic.common.exception.DuplicateException;
import com.lenoox.promusic.common.exception.PasswordIncorrectException;
import com.lenoox.promusic.common.exception.UserNotFoundException;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.Param.UserPasswordParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;
import com.lenoox.promusic.users.mapper.UserMapper;
import com.lenoox.promusic.users.mapper.UserPasswordMapper;
import com.lenoox.promusic.users.mapper.UserWithRolesMapper;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.UserRepository;
import com.lenoox.promusic.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${email.domain.auth}")
    private String emailDomainAuth;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private UserWithRolesMapper userWithRolesMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.entityToDto(user))
                .collect(Collectors.toList());
    }

    public UserWithRolesDTO getByUsername(String email) {
        User user = userRepository.getUserByUsername(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        UserWithRolesDTO userWithRolesDTO = userWithRolesMapper.entityToDto(user);

        return userWithRolesDTO;
    }

    @Override
    public UserDto save(UserParam userParam) {
        Optional<User> userWithDuplicateEmail = userRepository.getUsername(userParam.getUsername());
        if(userWithDuplicateEmail.isPresent()){
            log.error(String.format("Duplicate email %s", userParam.getUsername()));
            throw new DuplicateException(userParam.getUsername());
        }
        User user = userMapper.paramToEntity(userParam,emailDomainAuth,false);
        User userSaved = userRepository.save(user);
        UserDto userDto = userMapper.entityToDto(userSaved);
        return userDto;
    }
    @Override
    public UserDto update(String email, UserParam userParam) {
        if (!userRepository.getUsername(email).isPresent()) {
            throw new UserNotFoundException(email);
        }
        User user = userMapper.paramToEntity(userParam,email,true);
        User userSaved = userRepository.save(user);
        UserDto userDto = userMapper.entityToDto(userSaved);
        return userDto;
    }
    @Override
    public Boolean updatePassword(UserPasswordParam userPasswordParam,String email) {
        User userSaved = userRepository.getUserByUsername(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        boolean passwordmatches = passwordEncoder.matches(userPasswordParam.getOldPassword(), userSaved.getPassword());
        if(passwordmatches){
            User user = userPasswordMapper.paramToEntity(userSaved, userPasswordParam);
            userRepository.save(user);
            return true;
        } else{
            throw new PasswordIncorrectException();
        }
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
