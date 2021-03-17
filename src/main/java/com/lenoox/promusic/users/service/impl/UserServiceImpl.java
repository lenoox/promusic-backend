package com.lenoox.promusic.users.service.impl;

import com.lenoox.promusic.common.exception.DuplicateException;
import com.lenoox.promusic.common.models.RoleType;
import com.lenoox.promusic.users.Param.UserParam;
import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.dtos.UserWithRolesDTO;
import com.lenoox.promusic.users.models.Role;

import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.RoleRepository;
import com.lenoox.promusic.users.repository.UserRepository;
import com.lenoox.promusic.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            log.error("Invalid username or password.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    private Set<GrantedAuthority> getAuthorities(User user) {

        Role roleByUserId = user.getRole();
        final Set<GrantedAuthority> authorities = roleByUserId.getUsers().stream().map(role ->{
            log.info(role.getRole().getName().toString().toUpperCase());
            return new SimpleGrantedAuthority("ROLE_" + role.getRole().getName().toString().toUpperCase());
        }

        ).collect(Collectors.toSet());
        return authorities;
    }

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
        if(userWithDuplicateEmail != null && userParam.getId() != userWithDuplicateEmail.getId()) {
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