package com.lenoox.promusic.users.service.impl;

import com.lenoox.promusic.common.exception.DuplicateException;
import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<UserDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(User -> modelMapper.map(User, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserWithRolesDTO getByEamil(String email) {
        User user = userRepository.findByEmail(email);
        UserWithRolesDTO userWithRolesDTO = new UserWithRolesDTO();
        modelMapper.map(user, userWithRolesDTO);
        return userWithRolesDTO;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto save(UserParam userParam) {
        User userWithDuplicateEmail = userRepository.findByEmail(userParam.getEmail());
        if(userWithDuplicateEmail != null && userParam.getEmail() != userWithDuplicateEmail.getEmail()) {
            log.error(String.format("Duplicate email %s", userParam.getEmail()));
            throw new DuplicateException(userParam.getEmail());
        }
        User user = new User();
        user.setEmail(userParam.getEmail());
        user.setFirstName(userParam.getFirstName());
        user.setLastName(userParam.getLastName());
        user.setPassword(passwordEncoder.encode(userParam.getPassword()));
        if(userParam.getEmail().endsWith(emailDomainAuth)){
            user.setRole(roleRepository.findRole(RoleType.EMPLOYEE.toString()));
        } else{
            user.setRole(roleRepository.findRole(RoleType.USER.toString()));
        }
        user.setAddress(userParam.getAddress());
        user.setPhoneNumber(userParam.getPhoneNumber());
        user.setCity(userParam.getCity());
        user.setActive(false);
        userRepository.save(user);
        UserDto userDto = new UserDto();
        modelMapper.map(user, userDto);
        return userDto;
    }
}