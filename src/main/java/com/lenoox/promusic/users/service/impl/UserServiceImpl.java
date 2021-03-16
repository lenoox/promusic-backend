package com.lenoox.promusic.users.service.impl;

import com.lenoox.promusic.users.dtos.UserDto;
import com.lenoox.promusic.users.models.Role;
import com.lenoox.promusic.users.models.RoleType;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.RoleRepository;
import com.lenoox.promusic.users.repository.UserRepository;
import com.lenoox.promusic.users.service.UserService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String emailDomainAuth = "@gmail.com";

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if(user == null){
            log.error("Invalid username or password.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    private Set<GrantedAuthority> getAuthorities(User user) {

        Role roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.getUsers().stream().map(role ->{
                    log.info(role.getRoles().getName().toString().toUpperCase());
                   return new SimpleGrantedAuthority("ROLE_" + role.getRoles().getName().toString().toUpperCase());
        }

        ).collect(Collectors.toSet());
        return authorities;
    }

    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(user -> users.add(user.toUserDto()));
        return users;
    }

    public User findEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDto save(UserDto userDto) {
       // User userWithDuplicateUsername = userDao.findByUsername(userDto.getUsername());
       // if(userWithDuplicateUsername != null && userDto.getId() != userWithDuplicateUsername.getId()) {
       //     log.error(String.format("Duplicate username %", userDto.getUsername()));
       //     throw new RuntimeException("Duplicate username.");
       // }
        User userWithDuplicateEmail = userDao.findByEmail(userDto.getEmail());
        if(userWithDuplicateEmail != null && userDto.getId() != userWithDuplicateEmail.getId()) {
            log.error(String.format("Duplicate email %", userDto.getEmail()));
            throw new RuntimeException("Duplicate email.");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(userDto.getEmail().endsWith(emailDomainAuth)){
            user.setRoles(roleDao.findRole(RoleType.ADMIN.name()));
        } else{
            user.setRoles(roleDao.findRole(RoleType.EMPLOYEE.name()));
        }
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCity(userDto.getCity());
        user.setActive(userDto.getActive());
        userDao.save(user);
        return userDto;
    }
}