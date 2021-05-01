package com.lenoox.promusic.common.services;

import com.lenoox.promusic.common.exception.UserNotFoundException;
import com.lenoox.promusic.users.models.Role;
import com.lenoox.promusic.users.models.User;
import com.lenoox.promusic.users.repository.UserRepository;
import com.lenoox.promusic.users.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
@Transactional
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        if(user == null){
            log.error("Invalid username or password.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);

        return new UserDetailsImpl(user, grantedAuthorities);
    }
    public Set<GrantedAuthority> getAuthorities(User user) {
        Role roleByUserId = user.getRole();
        final Set<GrantedAuthority> authorities = roleByUserId
                .getUsers()
                .stream()
                .map(role -> {
                return new SimpleGrantedAuthority("ROLE_" + role.
                        getRole().
                        getName().
                        toString().toUpperCase());
            }
         ).collect(Collectors.toSet());
        return authorities;
    }
}
